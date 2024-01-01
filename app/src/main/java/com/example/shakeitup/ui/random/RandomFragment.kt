package com.example.shakeitup.ui.random

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.core.model.CocktailDetail
import com.example.shakeitup.core.service.RandomCocktailFetcher
import com.example.shakeitup.ui.details.CocktailDetailFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso


class RandomFragment : Fragment(), SensorEventListener, FragmentChangeListener {

    private lateinit var sensorManager: SensorManager
    private var lastUpdate: Long = 0
    private var last_x: Float = 0.0f
    private var last_y: Float = 0.0f
    private var last_z: Float = 0.0f
    private val SHAKE_THRESHOLD = 800
    private var shakeDetected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_random, container, false)

        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        return view
    }


    fun success(cocktailDetail: CocktailDetail, view: View) {
        requireActivity().runOnUiThread {
            Log.i("RANDOM", "SUCCESS")
            val shakeText: TextView = view.findViewById(R.id.shake_text)
            val cardRandom : CardView = view.findViewById(R.id.cocktail_random)
            val textCocktail : TextView = view.findViewById(R.id.cocktail_random_text)
            val imageCocktail : ImageView = view.findViewById(R.id.cocktail_random_image)
            cardRandom.visibility = View.VISIBLE
            textCocktail.text = cocktailDetail.name
            Picasso.get().load(cocktailDetail.drinkThumb).into(imageCocktail)

            cardRandom.setOnClickListener {
                onFragmentChange(CocktailDetailFragment.newInstance(
                    //CHANGE THIS BECAUSE IT IMPLIES AN UNECESSARY CALL TO THE DATABASE
                    Cocktail(cocktailDetail.name, cocktailDetail.drinkThumb, cocktailDetail.id)
                ))
            }

        }
    }
    fun error() {
        requireActivity().runOnUiThread {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage("Unable to load the categories")
                .setPositiveButton("Reload") { dialog, which ->

                    RandomCocktailFetcher.fetchRandomCocktail({ cocktailDetail -> view?.let {
                        success(cocktailDetail,
                            it
                        )
                    } },{error()})
                }
                .show()
        }
    }
    override fun onSensorChanged(event: SensorEvent) {
        val curTime = System.currentTimeMillis()

        if (!shakeDetected && (curTime - lastUpdate) > 100) {
            val diffTime = (curTime - lastUpdate).toFloat()
            lastUpdate = curTime

            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000

            if (speed > SHAKE_THRESHOLD) {
                Log.i("RANDOM", "SHAKE")
                shakeDetected = true
                val vibratorService = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibratorService.vibrate(250)

                RandomCocktailFetcher.fetchRandomCocktail({ cocktailDetail -> view?.let {
                    success(cocktailDetail,
                        it
                    )
                } },{error()})

                // Réinitialiser la variable shakeDetected après 100 ms
                Handler(Looper.getMainLooper()).postDelayed({
                    shakeDetected = false
                }, 500)
            }

            last_x = x
            last_y = y
            last_z = z
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        sensorManager.unregisterListener(this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters
         * @return A new instance of fragment RandomFragment.
         */
        @JvmStatic
        fun newInstance() =
            RandomFragment()
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val shakeText: TextView =view.findViewById(R.id.shake_text)
        val cardRandom : CardView = view.findViewById(R.id.cocktail_random)
        //val progressIndicator: CircularProgressIndicator = view.findViewById(R.id.progress_indicator)

        shakeText.visibility = View.VISIBLE
        cardRandom.visibility = View.GONE


       }

    override fun onFragmentChange(newFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, newFragment)
            .addToBackStack("")
            .commit()
    }

    }



