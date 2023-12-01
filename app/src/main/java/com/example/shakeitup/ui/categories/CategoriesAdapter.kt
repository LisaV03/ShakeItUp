package com.example.shakeitup.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Categories

class CategoriesAdapter(private val fragmentChangeListener: FragmentChangeListener, val categories : ArrayList<Categories>): Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.categories_layout, parent, false)
        return CategoriesViewHolder(itemView, fragmentChangeListener)
    }

    override fun getItemCount(): Int {
        return categories.size /2  + 1//Change with the number of categories
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.text1.text = categories[position*2].name
        //If there is an odd number of categories
        if (position*2 + 1 < categories.size) {
            holder.text2.text = categories[position*2 + 1].name
        } else {
            holder.card2.visibility = View.INVISIBLE
        }
    }
    }



