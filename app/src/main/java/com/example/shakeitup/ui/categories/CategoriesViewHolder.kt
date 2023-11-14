package com.example.shakeitup.ui.categories

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R

class CategoriesViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val textView: TextView

    init {
        textView = itemView.findViewById(R.id.category_text)
    }


}

