package com.example.shakeitup.ui.details

import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.core.model.CocktailDetail
import com.example.shakeitup.core.service.CocktailDetailFetcher
import com.example.shakeitup.ui.categories.CategoriesAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_COCKTAIL = "cocktail"

/**
 * A simple [Fragment] subclass.
 * Use the [CocktailDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CocktailDetailFragment : Fragment() {
    private var cocktail: Cocktail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cocktail = it.getSerializable(ARG_COCKTAIL) as Cocktail
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cocktail_detail, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment CocktailDetailFragment.
         */
        @JvmStatic
        fun newInstance(cocktail : Cocktail) =
            CocktailDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_COCKTAIL, cocktail)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var progressIndicator : ProgressBar = view.findViewById(R.id.progress_indicator)
        var details : RelativeLayout = view.findViewById(R.id.cocktail_detail)
        progressIndicator.visibility = View.VISIBLE
        details.visibility = View.GONE
        progressIndicator.isIndeterminate = true

        var name: TextView = view.findViewById(R.id.cocktail_name)
        name.text = cocktail?.name





        fun success(cocktailDetail: CocktailDetail) {

            requireActivity().runOnUiThread {

                Log.i("COCKTAIL", cocktailDetail.id.toString())

                val details : RelativeLayout = view.findViewById(R.id.cocktail_detail)
                val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_ingredient)
                val listIngredients : HashMap<String, String> = cocktailDetail.getIngredientsMap()
                val listIngredientsAdapter = ListIngredientsAdapter(listIngredients)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = listIngredientsAdapter

                var category: TextView = view.findViewById(R.id.cocktail_category)
                var glass: TextView = view.findViewById(R.id.cocktail_glass)
                var instructions: TextView = view.findViewById(R.id.cocktail_instructions)
                var progressIndicator : ProgressBar = view.findViewById(R.id.progress_indicator)

                category.text = cocktailDetail.category
                glass.text = cocktailDetail.glass
                instructions.text = cocktailDetail.instructionEN

                progressIndicator.visibility = View.GONE
                details.visibility = View.VISIBLE


            }


        }
        fun error() {
            requireActivity().runOnUiThread {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("Unable to load the categories")
                    .setPositiveButton("Reload") { dialog, which ->

                        cocktail?.id?.let {
                            CocktailDetailFetcher.fetchCocktailDetail(
                                it,
                                { cocktailDetail -> success(cocktailDetail) },
                                { error() })
                        }

                        }
                    .show()
            }
        }

        cocktail?.id?.let { CocktailDetailFetcher.fetchCocktailDetail(it, { cocktailDetail -> success(cocktailDetail)},{error()}) }
    }
}