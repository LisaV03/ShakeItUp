package com.example.shakeitup.ui.details

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.ui.details.CocktailDetailFragment
import com.google.android.material.card.MaterialCardView

class ListIngredientsViewHolder (itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val ingredient: TextView
    val measure: TextView

    init {

        ingredient = itemView.findViewById(R.id.cocktail_ingredients)
        measure = itemView.findViewById(R.id.cocktail_measures)


    }

}