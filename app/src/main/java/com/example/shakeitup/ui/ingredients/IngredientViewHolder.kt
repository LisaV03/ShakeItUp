package com.example.shakeitup.ui.ingredients

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R

class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ingredientTextView: TextView
        init {
            ingredientTextView = itemView.findViewById(R.id.ingredientTextView)
        }
}