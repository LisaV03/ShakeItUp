package com.example.shakeitup.core.model

import android.util.Log
import kotlin.collections.ArrayList


class ListIngredients(ingredients : HashMap<String, String>) {

    private var listIngredients = ingredients

    fun getlistIngredients() : HashMap<String,String> {
        return listIngredients
    }

    // Return the union of two listIngredients
    fun mergeIngredients(newListIngredients : ListIngredients) {
        newListIngredients.listIngredients.forEach { (ingredient, measure) ->
                if (this.listIngredients.isNullOrEmpty() || ! this.listIngredients.containsKey(ingredient)) {
                    this.listIngredients.put(ingredient, measure)
                }
            }
        }

    }
