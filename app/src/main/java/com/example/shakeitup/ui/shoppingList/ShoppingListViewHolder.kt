package com.example.shakeitup.ui.shoppingList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener

class ShoppingListViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val ingredient: TextView
    val ingredientImageView : ImageView
    init {

        ingredient = itemView.findViewById(R.id.cocktail_ingredients)
        ingredientImageView = itemView.findViewById(R.id.ingredientImageView)

    }

}