package com.example.shakeitup.ui.listCocktails

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Cocktail
import com.squareup.picasso.Picasso

class ListCocktailAdapter(private val fragmentChangeListener: FragmentChangeListener, val cocktails: ArrayList<Cocktail>) : RecyclerView.Adapter<ListCocktailViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCocktailViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_cocktails_layout, parent, false)
            return ListCocktailViewHolder(itemView, fragmentChangeListener)
        }

        override fun getItemCount(): Int {
            return cocktails.size
        }

        override fun onBindViewHolder(holder: ListCocktailViewHolder, position: Int) {
            holder.textView.text = cocktails[position].name
            holder.setCocktailClicked(cocktails[position])
            Picasso.get().load(cocktails[position].image).into(holder.imageView)
        }



    }
