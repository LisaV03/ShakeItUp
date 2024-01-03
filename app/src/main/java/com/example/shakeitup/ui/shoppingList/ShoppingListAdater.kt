package com.example.shakeitup.ui.shoppingList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.ListIngredients
import com.squareup.picasso.Picasso


class ShoppingListAdater (val ingredients: ListIngredients) : RecyclerView.Adapter<ShoppingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_ingredients_shopping_layout, parent, false)
        return ShoppingListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ingredients.getlistIngredients().size
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val keys : List<String> = ingredients.getlistIngredients().keys.toList()
        holder.ingredient.text = keys[position]
        Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/"+keys[position].lowercase()+".png").into(holder.ingredientImageView)

    }



}
