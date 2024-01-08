package com.example.shakeitup.ui.listCocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.core.service.CocktailsFetcher
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator

private const val ARG_NAME = "name"
private const val ARG_TYPE = "type"


class ListCocktailFragment : Fragment(), FragmentChangeListener {
    private var name: String? = null
    private var type: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            type = it.getInt(ARG_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_cocktail, container, false)
    }

    companion object {
        /**
         *
         * @param name of the category or of the ingredient.
         * @param type 0 if it's from a category or 1 if it's from an ingredient
         * @return A new instance of fragment ListCocktailFragment.
         */
        @JvmStatic
        fun newInstance(name: String, type: Int) =
            ListCocktailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putInt(ARG_TYPE, type)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView: RecyclerView =view.findViewById(R.id.recycler_view_categories)
        val progressIndicator: CircularProgressIndicator = view.findViewById(R.id.progress_indicator)
        var text : TextView = view.findViewById(R.id.cocktails_category)

        text.text = name

        recyclerView.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE
        progressIndicator.isIndeterminate = true


        fun success(cocktails : ArrayList<Cocktail>) {
            requireActivity().runOnUiThread {
                val cocktailAdapter = ListCocktailAdapter(this, cocktails)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = cocktailAdapter

                recyclerView.visibility = View.VISIBLE
                progressIndicator.visibility = View.GONE
            }
        }
        fun error() {
            requireActivity().runOnUiThread {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("No Internet connection available")
                    .setPositiveButton("Reload") { dialog, which ->

                        type?.let {
                            CocktailsFetcher.fetchCocktails(text.text.toString(),
                                it, { cocktails -> success(cocktails )},{error()})
                        }
                    }
                    .show()
            }
        }

        type?.let {
            CocktailsFetcher.fetchCocktails(text.text.toString(),
                it,  { cocktails -> success(cocktails )},{error()})
        }


    }

    override fun onFragmentChange(newFragment: Fragment) {
        var nameFragment : String = "IngredientsFragment"
        if (type == 0) {
            nameFragment = "CategoriesFragment"
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, newFragment)
            .addToBackStack(nameFragment)
            .commit()
    }
}
