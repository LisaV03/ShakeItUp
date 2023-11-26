package com.example.shakeitup.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener


class ListIngredientsAdapter (val ingredients: HashMap<String, String>) : RecyclerView.Adapter<ListIngredientsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListIngredientsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_ingredients_layout, parent, false)
        return ListIngredientsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        Log.i("INGREDIENTS", ingredients.size.toString())
        return ingredients.size

    }

    override fun onBindViewHolder(holder: ListIngredientsViewHolder, position: Int) {
        val keys : List<String> = ingredients.keys.toList()
        holder.ingredient.text = keys[position]
        holder.measure.text = ingredients.get(keys[position])

        Log.i("INGREDIENTS", keys[position])
        ingredients.get(keys[position])?.let { Log.i("INGREDIENTS", it) }
    }



}
