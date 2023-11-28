package com.example.shakeitup.ui.categories

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.ui.listCocktails.ListCocktailFragment
import com.google.android.material.card.MaterialCardView


class CategoriesViewHolder(itemView: View, private val fragmentChangeListener: FragmentChangeListener) :
    RecyclerView.ViewHolder(itemView) {

    val textView: TextView
    val cardView: MaterialCardView

    init {

        textView = itemView.findViewById(R.id.category_text)
        cardView = itemView.findViewById(R.id.category_card)

        cardView.setOnClickListener {
            fragmentChangeListener.onFragmentChange(ListCocktailFragment.newInstance(textView.text.toString(), 0))
        }

        }








}

