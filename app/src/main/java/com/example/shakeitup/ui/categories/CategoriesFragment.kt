package com.example.shakeitup.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Categories
import com.example.shakeitup.core.service.CategoriesFetcher
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator

class CategoriesFragment : Fragment(), FragmentChangeListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        initializeUI(view)
        fetchCategories()
        return view
    }

    private fun initializeUI(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_categories)
        progressIndicator = view.findViewById(R.id.progress_indicator)
        recyclerView.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE
        progressIndicator.isIndeterminate = true
    }

    private fun fetchCategories() {
        val categoriesFetcher = CategoriesFetcher()
        categoriesFetcher.fetchData<Categories>(
            success = { categories -> requireActivity().runOnUiThread {updateUI(categories)} },
            failure = { requireActivity().runOnUiThread {showErrorDialog()} }
        )
    }

    private fun updateUI(categories: ArrayList<Categories>) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = context?.let { CategoriesAdapter(this, categories, it) }
        recyclerView.visibility = View.VISIBLE
        progressIndicator.visibility = View.GONE
    }

    private fun showErrorDialog() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setMessage("Unable to load the categories")
            setPositiveButton("Reload") { _, _ -> fetchCategories() }
        }.show()
    }

    //Deal with the changes of fragment in a fragment
    override fun onFragmentChange(newFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, newFragment)
            .addToBackStack("CategoriesFragment")
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }
}