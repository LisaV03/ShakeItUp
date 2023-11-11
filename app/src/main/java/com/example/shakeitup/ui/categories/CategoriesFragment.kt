package com.example.shakeitup.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.model.Categories
import com.example.shakeitup.core.service.CategoriesFetcher
import com.google.android.material.progressindicator.CircularProgressIndicator


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {

    // TODO: Rename and change types of parameters
   // private var param1: String? = null
    //private var param2: String? = null

   // override fun onCreate(savedInstanceState: Bundle?) {
     //   super.onCreate(savedInstanceState)
       // arguments?.let {
        //}
    //}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoriesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance()=//param1: String, param2: String) =
            CategoriesFragment()//.apply {
             //   arguments = Bundle().apply {
              //      putString(ARG_PARAM1, param1)
                //    putString(ARG_PARAM2, param2)
                //}
            //}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView:RecyclerView=view.findViewById(R.id.recycler_view_categories)

        val progressIndicator: CircularProgressIndicator = view.findViewById(R.id.progress_indicator)

        recyclerView.visibility = View.GONE
        progressIndicator.visibility = View.VISIBLE
        progressIndicator.isIndeterminate = true


        CategoriesFetcher.fetchCategories { categories ->
            requireActivity().runOnUiThread {
                val categoriesAdapter = CategoriesAdapter(categories)
                // Set the LayoutManager that
                // this RecyclerView will use.


                recyclerView.layoutManager = LinearLayoutManager(context)

                // adapter instance is set to the
                // recyclerview to inflate the items.
                recyclerView.adapter = categoriesAdapter
                recyclerView.visibility = View.VISIBLE
                progressIndicator.visibility = View.GONE
            }
        }






    }
}
