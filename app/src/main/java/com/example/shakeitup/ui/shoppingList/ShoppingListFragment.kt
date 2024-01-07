package com.example.shakeitup.ui.shoppingList

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakeitup.R
import com.example.shakeitup.core.model.ListIngredients
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ShoppingListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ShoppingListFragment()
    }


    fun displaySharedPreferences(recyclerView : RecyclerView, button : Button, textEmpty : TextView){
        var listIngredients : ListIngredients = ListIngredients(HashMap<String, String>())
        val gson = Gson()
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            val listIngredientsString = sharedPref.getString("list_shopping", "")
            if (!listIngredientsString.isNullOrEmpty()) {
                val type = object : TypeToken<ListIngredients>() {}.type
                listIngredients =  gson.fromJson(listIngredientsString, type)

                button.visibility = View.VISIBLE
                textEmpty.visibility = View.GONE
            }
        }
        val shoppingListAdapter = ShoppingListAdapter(listIngredients)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = shoppingListAdapter

    }

    fun clearSharedPreferences(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.clear()
        editor?.apply()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_shopping)
        var buttonEmpty: Button = view.findViewById(R.id.button_empty)
        var textEmpty : TextView = view.findViewById(R.id.text_empty)

        buttonEmpty.visibility = View.GONE
        textEmpty.visibility = View.VISIBLE

        displaySharedPreferences(recyclerView, buttonEmpty, textEmpty)

        buttonEmpty.setOnClickListener {
            clearSharedPreferences()
            displaySharedPreferences(recyclerView, buttonEmpty, textEmpty)
            buttonEmpty.visibility = View.GONE
            textEmpty.visibility = View.VISIBLE
        }

    }
}



