package com.example.shakeitup.ui.listCocktails

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.ui.details.CocktailDetailFragment
import com.google.android.material.card.MaterialCardView

class ListCocktailViewHolder (itemView: View, private val fragmentChangeListener: FragmentChangeListener) :
        RecyclerView.ViewHolder(itemView) {

    val textView: TextView
    val cardView: MaterialCardView
    lateinit var cocktail : Cocktail

    init {

        textView = itemView.findViewById(R.id.cocktail_text)
        cardView = itemView.findViewById(R.id.cocktail_card)


        cardView.setOnClickListener {
            fragmentChangeListener.onFragmentChange(CocktailDetailFragment.newInstance(cocktail))
        }

    }

    fun setCocktailClicked(cocktailReceived : Cocktail){
        cocktail = cocktailReceived
        Log.i("VIEWHOLDER", "set cocktail, name: "+ cocktail.name)
    }
}