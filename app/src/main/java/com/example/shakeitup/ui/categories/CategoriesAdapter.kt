package com.example.shakeitup.ui.categories

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.shakeitup.R
import com.example.shakeitup.core.Utils.FragmentChangeListener
import com.example.shakeitup.core.model.Categories
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.Locale

class CategoriesAdapter(private val fragmentChangeListener: FragmentChangeListener, val categories : ArrayList<Categories>, val context: Context): Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.categories_layout, parent, false)
        return CategoriesViewHolder(itemView, fragmentChangeListener)
    }

    override fun getItemCount(): Int {
        return categories.size /2  + 1//Change with the number of categories
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.text1.text = categories[position*2].name
        Log.i("Name", categories[position*2].name)
        holder.image1.setImageResource(getImageResource(categories[position*2].name))
        //If there is an odd number of categories
        if (position*2 + 1 < categories.size) {
            holder.text2.text = categories[position*2 + 1].name
            Log.i("Name", categories[position*2+1].name)
            holder.image2.setImageResource(getImageResource(categories[position*2+1].name))
        } else {
            holder.card2.visibility = View.INVISIBLE
        }
    }

    fun getImageResource(name: String): Int{

        var imageName = name.replace(" / ", "_")
        imageName = imageName.replace(" ", "_")
        imageName = imageName.lowercase()
        Log.i("Name", imageName)
        val resources1 : Int = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return resources1
    }
    }



