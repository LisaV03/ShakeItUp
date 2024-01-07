package com.example.shakeitup.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.core.service.CocktailsFetcher
import com.example.shakeitup.ui.listCocktails.ListCocktailAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator

class SearchFragment : Fragment(), FragmentChangeListener {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var cocktailAdapter: ListCocktailAdapter
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initializeUI(view)
        setupSearchView()
        fetchCocktails()
        return view
    }

    private fun initializeUI(view: View) {
        searchView = view.findViewById(R.id.search_view)

        recyclerView = view.findViewById(R.id.recycler_view_cocktails)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        progressIndicator = view.findViewById(R.id.progress_indicator)
        recyclerView.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE
        progressIndicator.isIndeterminate = true
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("Search", "Final query: $query")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("Search", "This is the tmp query $newText")
                cocktailAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun updateUI(cocktails: ArrayList<Cocktail>) {
        cocktailAdapter = ListCocktailAdapter(this, cocktails)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cocktailAdapter
        recyclerView.visibility = View.VISIBLE
        progressIndicator.visibility = View.GONE
    }

    private fun fetchCocktails() {
/*        CocktailsFetcher.fetchCocktails(
            name = "shake",
            type = 0,
            success = { cocktails -> requireActivity().runOnUiThread { updateUI(cocktails) } },
            failure = { requireActivity().runOnUiThread { showErrorDialog() } }
        )*/

        CocktailsFetcher.fetchAllCocktails(
            success = { cocktails -> requireActivity().runOnUiThread { updateUI(cocktails) } },
            failure = { requireActivity().runOnUiThread { showErrorDialog() } }
        )
    }

    private fun showErrorDialog() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setMessage("Unable to load the cocktails")
            setPositiveButton("Reload") { _, _ -> fetchCocktails() }
        }.show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    override fun onFragmentChange(newFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, newFragment)
            .addToBackStack("SearchFragment")
            .commit()
    }
}