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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "name"
private const val ARG_PARAM2 = "type"


/**
 * A simple [Fragment] subclass.
 * Use the [ListCocktailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListCocktailFragment : Fragment(), FragmentChangeListener {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private var type: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_PARAM1)
            type = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_list_cocktail, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment ListCocktailFragment.
         */
        @JvmStatic
        fun newInstance(name: String, type: Int) =
            ListCocktailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, name)
                    putInt(ARG_PARAM2, type)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var text : TextView = view.findViewById(R.id.cocktails_category)
        text.text = name
        val recyclerView: RecyclerView =view.findViewById(R.id.recycler_view_categories)
        val progressIndicator: CircularProgressIndicator = view.findViewById(R.id.progress_indicator)

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
                    .setMessage("Unable to load the categories")
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
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, newFragment)
            .addToBackStack("")
            .commit()
    }
}
