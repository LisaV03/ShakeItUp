package com.example.shakeitup.ui.details

import android.content.Context
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.core.model.CocktailDetail
import com.example.shakeitup.core.model.ListIngredients
import com.example.shakeitup.core.service.CocktailDetailFetcher
import com.example.shakeitup.ui.categories.CategoriesAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso

private const val ARG_COCKTAIL = "cocktail"

class CocktailDetailFragment : Fragment() {
    private var cocktail: Cocktail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cocktail = it.getSerializable(ARG_COCKTAIL) as Cocktail
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cocktail_detail, container, false)
    }

    companion object {
        /**
         * @param cocktail
         * @return A new instance of fragment CocktailDetailFragment.
         */
        @JvmStatic
        fun newInstance(cocktail : Cocktail) =
            CocktailDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_COCKTAIL, cocktail)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var progressIndicator : ProgressBar = view.findViewById(R.id.progress_indicator)
        var details : RelativeLayout = view.findViewById(R.id.cocktail_detail)
        var name: TextView = view.findViewById(R.id.cocktail_name)

        name.text = cocktail?.name

        progressIndicator.visibility = View.VISIBLE
        details.visibility = View.GONE
        progressIndicator.isIndeterminate = true










        fun success(cocktailDetail: CocktailDetail) {

            requireActivity().runOnUiThread {

                Log.i("COCKTAIL", cocktailDetail.id.toString())

                val details : RelativeLayout = view.findViewById(R.id.cocktail_detail)
                val image : ImageView = view.findViewById(R.id.cocktail_detail_image)
                val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_ingredient)
                val listIngredientsNew : ListIngredients = cocktailDetail.getIngredientsMap()

                Picasso.get().load(cocktailDetail.drinkThumb).into(image)

                val listIngredientsAdapter = ListIngredientsAdapter(listIngredientsNew)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = listIngredientsAdapter

                var category: TextView = view.findViewById(R.id.cocktail_category)
                var glass: TextView = view.findViewById(R.id.cocktail_glass)
                var instructions: TextView = view.findViewById(R.id.cocktail_instructions)
                var progressIndicator : ProgressBar = view.findViewById(R.id.progress_indicator)

                category.text = cocktailDetail.category
                glass.text = cocktailDetail.glass
                instructions.text = cocktailDetail.instructionEN

                progressIndicator.visibility = View.GONE
                details.visibility = View.VISIBLE

                var button: Button = view.findViewById(R.id.button_shopping)
                button.setOnClickListener {
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    val gson = Gson()
                    var listIngredientsGson = gson.toJson(listIngredientsNew)
                    if (sharedPref != null){
                        val listIngredientsString = sharedPref.getString("list_shopping", "")
                        if (!listIngredientsString.isNullOrEmpty()) {
                            val type = object : TypeToken<ListIngredients>() {}.type
                            val listIngredients = gson.fromJson<ListIngredients>(listIngredientsString, type)
                            listIngredients.mergeIngredients(listIngredientsNew)
                            listIngredientsGson = gson.toJson(listIngredients)
                        }
                        val editor = sharedPref.edit()
                        editor.putString("list_shopping", listIngredientsGson)
                        editor.apply()
                        val snackbar = Snackbar.make(it, "Ingredients added to the Shopping list", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }
                }
            }


        }
        fun error() {
            requireActivity().runOnUiThread {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("No Internet connection available")
                    .setPositiveButton("Reload") { dialog, which ->

                        cocktail?.id?.let {
                            CocktailDetailFetcher.fetchCocktailDetail(
                                it,
                                { cocktailDetail -> success(cocktailDetail) },
                                { error() })
                        }

                        }
                    .show()
            }
        }

        cocktail?.id?.let { CocktailDetailFetcher.fetchCocktailDetail(it, { cocktailDetail -> success(cocktailDetail)},{error()}) }
    }
}