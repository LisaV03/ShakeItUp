package com.example.shakeitup.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cocktail(name: String, image: String, id: Int) : Serializable{
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