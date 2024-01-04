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
import com.example.shakeitup.core.model.Ingredients
import com.example.shakeitup.core.service.IngredientsFetcher
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator

class IngredientsFragment : Fragment(), FragmentChangeListener {

    private lateinit var recyclerViewIngredients: RecyclerView
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingredients, container, false)
        initializeUI(view)
        fetchIngredients()
        return view
    }

    private fun initializeUI(view: View) {
        recyclerViewIngredients = view.findViewById(R.id.recycler_view_ingredient)
        progressIndicator = view.findViewById(R.id.progress_indicator)

        recyclerViewIngredients.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE
        progressIndicator.isIndeterminate = true
    }

    private fun fetchIngredients() {
        val ingredientsFetcher = IngredientsFetcher()
        ingredientsFetcher.fetchData(
            success = { ingredients -> requireActivity().runOnUiThread { updateUI(ingredients) } },
            failure = { requireActivity().runOnUiThread { showErrorDialog() } }
        )
    }

    private fun updateUI(ingredients: ArrayList<Ingredients>) {
        val ingredientAdapter = IngredientAdapter(this, ingredients)
        recyclerViewIngredients.layoutManager = LinearLayoutManager(context)
        recyclerViewIngredients.adapter = ingredientAdapter
        recyclerViewIngredients.visibility = View.VISIBLE
        progressIndicator.visibility = View.GONE
    }

    private fun showErrorDialog() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setMessage("Unable to load the ingredients")
            setPositiveButton("Reload") { _, _ -> fetchIngredients() }
        }.show()
    }

    override fun onFragmentChange(newFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, newFragment)
            .addToBackStack("")
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = IngredientsFragment()
    }
}
