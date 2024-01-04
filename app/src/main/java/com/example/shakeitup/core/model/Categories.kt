package com.example.shakeitup.core.model

import com.google.gson.annotations.SerializedName

class Categories(name: String) {
    @SerializedName("strCategory")
    val name: String = name
}

class CategoryResponse(
    @SerializedName("drinks") val drinks: List<Categories>
)