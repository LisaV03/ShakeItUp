package com.example.shakeitup.ui.listCocktails

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.google.android.material.card.MaterialCardView

class ListCocktailViewHolder (itemView: View, private val fragmentChangeListener: FragmentChangeListener) :
        RecyclerView.ViewHolder(itemView) {

    val textView: TextView
    val cardView: MaterialCardView

    init {

        textView = itemView.findViewById(R.id.cocktail_text)
        cardView = itemView.findViewById(R.id.cocktail_card)

        cardView.setOnClickListener {
            //TODO Change with the coktail receipe Fragment
            //fragmentChangeListener.onFragmentChange(ListCocktailFragment.newInstance(textView.text.toString()))
        }

    }
}