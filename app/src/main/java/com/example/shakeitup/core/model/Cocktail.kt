package com.example.shakeitup.core.model

import com.google.gson.annotations.SerializedName

class Cocktail(name: String, image: String, id: Int) {
    @SerializedName("strDrink")
    val name: String = name

    @SerializedName("strDrinkThumb")
    val image: String = image

    @SerializedName("idDrink")
    val id: Int = id
}

class CocktailsResponse(
    @SerializedName("drinks") val drinks: List<Cocktail>
)
