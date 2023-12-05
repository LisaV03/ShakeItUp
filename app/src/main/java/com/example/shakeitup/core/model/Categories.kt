package com.example.shakeitup.core.model
import android.content.res.Resources
import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

class Categories(name: String) {
    @SerializedName("strCategory")
    val name: String = name
}

class CategoryResponse(
    @SerializedName("drinks") val drinks: List<Categories>
)
