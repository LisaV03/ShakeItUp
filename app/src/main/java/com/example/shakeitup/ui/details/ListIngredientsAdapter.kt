package com.example.shakeitup.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.ListIngredients


class ListIngredientsAdapter (val ingredients: ListIngredients) : RecyclerView.Adapter<ListIngredientsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListIngredientsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_ingredients_layout, parent, false)
        return ListIngredientsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ingredients.getlistIngredients().size

    }

    override fun onBindViewHolder(holder: ListIngredientsViewHolder, position: Int) {
        val keys : List<String> = ingredients.getlistIngredients().keys.toList()
        holder.ingredient.text = keys[position]
        holder.measure.text = ingredients.getlistIngredients().get(keys[position])
    }



}
