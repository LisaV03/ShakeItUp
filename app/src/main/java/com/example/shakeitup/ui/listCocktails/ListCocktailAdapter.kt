package com.example.shakeitup.ui.listCocktails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Cocktail

class ListCocktailAdapter(private val fragmentChangeListener: FragmentChangeListener, val cocktails: ArrayList<Cocktail>) : RecyclerView.Adapter<ListCocktailViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCocktailViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_cocktails_layout, parent, false)
            return ListCocktailViewHolder(itemView, fragmentChangeListener)
        }

        override fun getItemCount(): Int {
            return cocktails.size //Change with the number of categories
        }

        override fun onBindViewHolder(holder: ListCocktailViewHolder, position: Int) {
            holder.textView.text = cocktails[position].name
        }



    }
