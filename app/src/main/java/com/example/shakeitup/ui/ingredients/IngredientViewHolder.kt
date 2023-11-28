package com.example.shakeitup.ui.ingredients

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.ui.listCocktails.ListCocktailFragment
import com.google.android.material.card.MaterialCardView

class IngredientViewHolder(itemView: View, private val fragmentChangeListener: FragmentChangeListener) : RecyclerView.ViewHolder(itemView) {
    val ingredientTextView: TextView
    val ingredientCardView: MaterialCardView
        init {
            ingredientTextView = itemView.findViewById(R.id.ingredientTextView)
            ingredientCardView = itemView.findViewById(R.id.card_ingredient)

            ingredientCardView.setOnClickListener {
                fragmentChangeListener.onFragmentChange(ListCocktailFragment.newInstance(ingredientTextView.text.toString(), 1))
            }

        }


}