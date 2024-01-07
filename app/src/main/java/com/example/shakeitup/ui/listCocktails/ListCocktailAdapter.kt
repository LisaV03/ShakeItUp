package com.example.shakeitup.ui.listCocktails

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Cocktail
import com.squareup.picasso.Picasso
import java.util.Locale

class ListCocktailAdapter(private val fragmentChangeListener: FragmentChangeListener, val cocktails: ArrayList<Cocktail>) : RecyclerView.Adapter<ListCocktailViewHolder>(),
    Filterable {

    private var cocktailsList: List<Cocktail> = cocktails
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCocktailViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_cocktails_layout, parent, false)
            return ListCocktailViewHolder(itemView, fragmentChangeListener)
        }

        override fun getItemCount(): Int {
            return cocktailsList.size
        }

        override fun onBindViewHolder(holder: ListCocktailViewHolder, position: Int) {
            holder.textView.text = cocktailsList[position].name
            holder.setCocktailClicked(cocktailsList[position])
            Picasso.get().load(cocktailsList[position].image).into(holder.imageView)
        }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                cocktailsList = filterResults.values as List<Cocktail>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.lowercase(Locale.ROOT)

                val filterResults = FilterResults()
                filterResults.values = if (queryString.isNullOrEmpty())
                    cocktails
                else
                    cocktails.filter {
                        it.name.lowercase(Locale.ROOT).contains(queryString)
                    }
                return filterResults
            }
        }
    }



    }
