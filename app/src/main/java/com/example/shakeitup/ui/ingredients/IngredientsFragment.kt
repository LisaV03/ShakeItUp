package com.example.shakeitup.ui.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.service.IngredientsFetcher
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator

/**
 * A simple [Fragment] subclass.
 * Use the [IngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IngredientsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewIngredients: RecyclerView = view.findViewById(R.id.recycler_view_ingredient)
/*        val progressIndicator: CircularProgressIndicator = view.findViewById(R.id.progress_indicator)

        recyclerView.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE
        progressIndicator.isIndeterminate = true*/

        fun success(ingredients : ArrayList<String>) {
            requireActivity().runOnUiThread {
                val ingredientAdapter = IngredientAdapter(ingredients)
                recyclerViewIngredients.layoutManager = LinearLayoutManager(context)
                recyclerViewIngredients.adapter = ingredientAdapter
                recyclerViewIngredients.visibility = View.VISIBLE
//                progressIndicator.visibility = View.GONE
            }
        }
        fun error() {
            requireActivity().runOnUiThread {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("Unable to load the ingredients")
                    .setPositiveButton("Reload") { dialog, which ->
                        IngredientsFetcher.fetchIngredients({ ingredients -> success(ingredients)},{error()})
                    }
                    .show()
            }
        }
        IngredientsFetcher.fetchIngredients({ ingredients -> success(ingredients)},{error()})
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment IngredientsFragment.
         */
        @JvmStatic
        fun newInstance() = IngredientsFragment()
    }
}