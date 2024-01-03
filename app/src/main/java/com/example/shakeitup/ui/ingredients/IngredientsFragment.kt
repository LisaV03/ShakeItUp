package com.example.shakeitup.ui.ingredients

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.service.IngredientsFetcher
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator

class IngredientsFragment : Fragment(), FragmentChangeListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() = IngredientsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewIngredients: RecyclerView = view.findViewById(R.id.recycler_view_ingredient)
        val progressIndicator: CircularProgressIndicator = view.findViewById(R.id.progress_indicator)

        recyclerViewIngredients.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE
        progressIndicator.isIndeterminate = true

        fun success(ingredients : ArrayList<String>) {
            requireActivity().runOnUiThread {
                val ingredientAdapter = IngredientAdapter(this, ingredients)
                recyclerViewIngredients.layoutManager = LinearLayoutManager(context)
                recyclerViewIngredients.adapter = ingredientAdapter

                recyclerViewIngredients.visibility = View.VISIBLE
                progressIndicator.visibility = View.GONE
            }
        }
        fun error() {
            requireActivity().runOnUiThread {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("No Internet connection available")
                    .setPositiveButton("Reload") { dialog, which ->
                        IngredientsFetcher.fetchIngredients({ ingredients -> success(ingredients)},{error()})
                    }
                    .show()
            }
        }
        IngredientsFetcher.fetchIngredients({ ingredients -> success(ingredients)},{error()})
    }


    override fun onFragmentChange(newFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, newFragment)
            .addToBackStack("IngredientsFragment")
            .commit()
    }


}