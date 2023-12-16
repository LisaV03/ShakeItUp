package com.example.shakeitup.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.model.Cocktail
import java.util.Locale

class CocktailAdapter(private val context: Context, private val dataset: List<Cocktail>):RecyclerView.Adapter<CocktailAdapter.ItemViewHolder>(), Filterable{
    private var cocktailsList: List<Cocktail> = dataset
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)

    }

    override fun getItemCount(): Int {
        return cocktailsList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = cocktailsList[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
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
                    dataset
                else
                    dataset.filter {
                        context.resources.getString(it.stringResourceId).lowercase(Locale.ROOT).contains(queryString)
                    }
                return filterResults
            }
        }
    }


}