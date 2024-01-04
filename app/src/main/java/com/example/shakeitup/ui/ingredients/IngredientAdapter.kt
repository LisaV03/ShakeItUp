package com.example.shakeitup.ui.ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener

import com.example.shakeitup.core.model.Ingredients
import com.squareup.picasso.Picasso

class IngredientAdapter(private val fragmentChangeListener: FragmentChangeListener, val ingredients: ArrayList<Ingredients>) : Adapter<IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.ingredient_layout, parent, false)
        return IngredientViewHolder(view, fragmentChangeListener)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient: String = ingredients[position].name
        holder.ingredientTextView.text = ingredient
        Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/"+ingredient.lowercase()+".png").into(holder.ingredientImageView)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}