package com.example.shakeitup.ui.categories

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.ui.listCocktails.ListCocktailFragment
import com.google.android.material.card.MaterialCardView


class CategoriesViewHolder(itemView: View, private val fragmentChangeListener: FragmentChangeListener) :
    RecyclerView.ViewHolder(itemView) {

    val text1: TextView
    val card1: MaterialCardView
    val image1: ImageView

    val text2: TextView
    val card2: MaterialCardView
    val image2: ImageView

    init {

        text1 = itemView.findViewById(R.id.category_text)
        card1 = itemView.findViewById(R.id.category_card)
        image1 = itemView.findViewById(R.id.category_image)

        text2 = itemView.findViewById(R.id.category2_text)
        card2 = itemView.findViewById(R.id.category2_card)
        image2 = itemView.findViewById(R.id.category2_image)

        card1.setOnClickListener {
            fragmentChangeListener.onFragmentChange(ListCocktailFragment.newInstance(text1.text.toString(), 0))
        }

        card2.setOnClickListener {
            fragmentChangeListener.onFragmentChange(ListCocktailFragment.newInstance(text2.text.toString(), 0))
        }

        }

}

