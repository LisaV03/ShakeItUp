package com.example.shakeitup.core.model

import com.google.gson.annotations.SerializedName

class Ingredients(name: String) {
    @SerializedName("strIngredient1")
    val name: String = name
}