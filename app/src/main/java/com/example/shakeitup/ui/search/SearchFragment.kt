package com.example.shakeitup.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.core.service.CocktailsFetcher
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var searchBar: SearchBar
    private lateinit var data : List<Cocktail>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        data = CocktailsFetcher().loadCocktails()
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            Log.i("Search", "My key $keyCode")
            return true
        }
        return false
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_cocktails)
        Log.i("Test", "My data $data")
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL /*or LinearLayoutManager.HORIZONTAL*/)
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = context?.let { CocktailAdapter(it, data) }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}