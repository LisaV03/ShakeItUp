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


/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment(), FragmentChangeListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance()=
            CategoriesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView:RecyclerView=view.findViewById(R.id.recycler_view_categories)
        val progressIndicator: CircularProgressIndicator = view.findViewById(R.id.progress_indicator)

        recyclerView.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE
        progressIndicator.isIndeterminate = true

        fun success(categories : ArrayList<Categories>) {
            requireActivity().runOnUiThread {
                val categoriesAdapter = context?.let { CategoriesAdapter(this, categories, it) }

                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = categoriesAdapter
                recyclerView.visibility = View.VISIBLE
                progressIndicator.visibility = View.GONE
            }
        }
        fun error() {
            requireActivity().runOnUiThread {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("Unable to load the categories")
                    .setPositiveButton("Reload") { dialog, which ->

                        CategoriesFetcher.fetchCategories({ categories -> success(categories)},{error()})
                    }
                    .show()
            }
        }

        CategoriesFetcher.fetchCategories({ categories -> success(categories)},{error()})


    }


    override fun onFragmentChange(newFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, newFragment)
            .addToBackStack("")
            .commit()
    }



}
