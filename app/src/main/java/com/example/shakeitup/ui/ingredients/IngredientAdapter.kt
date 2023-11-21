package com.example.shakeitup.ui.ingredients

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R

class IngredientAdapter(private val ingredients: List<String>) : RecyclerView.Adapter<IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient: String = ingredients[position]
        holder.ingredientTextView.text = ingredient
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}