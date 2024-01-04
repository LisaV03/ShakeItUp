package com.example.shakeitup.core.model

import android.util.Log
import com.google.gson.annotations.SerializedName



class CocktailDetail(id: Int, name: String, drinkAlternate : String, tags : String, video : String,
                     category : String, iba: String, alcoholic : String, glass : String, instructionEN : String,
                     instructionES: String, instructionDE: String, instructionFR: String, instructionIT: String,
                     instructionZH_HANS: String, instructionZH_HANT: String, drinkThumb : String, i1 : String,
                     i2: String, i3 : String, i4 : String, i5 : String, i6 : String, i7: String, i8 : String,
                     i9 : String, i10 : String, i11 : String, i12: String, i13 : String, i14 : String, i15 : String,
                     m1 : String, m2: String, m3 : String, m4 : String, m5 : String, m6 : String, m7: String,
                     m8 : String, m9 : String, m10 : String, m11 : String, m12: String, m13 : String, m14 : String,
                     m15 : String, imageSource : String, imageAttribution : String, creativeCommonsConfirmed : String,
                     dateModified : String ) {
    @SerializedName("idDrink")
    val id: Int = id

    @SerializedName("strDrink")
    val name: String = name

    @SerializedName("strDrinkAlternate")
    val drinkAlternate: String = drinkAlternate

    @SerializedName("strTags")
    val tags: String = tags

    @SerializedName("strVideo")
    val video: String = video

    @SerializedName("strCategory")
    val category: String = category

    @SerializedName("strIBA")
    val iba: String = iba

    @SerializedName("strAlcoholic")
    val alcoholic : String = alcoholic

    val isAlcoholic : Boolean = (alcoholic == "Alcoholic")

    @SerializedName("strGlass")
    val glass : String = glass

    @SerializedName("strInstructions")
    val instructionEN : String = instructionEN

    @SerializedName("strInstructionsES")
    val instructionES: String = instructionES

    @SerializedName("strInstructionsDE")
    val instructionDE : String = instructionDE

    @SerializedName("strInstructionsFR")
    val instructionFR : String = instructionFR

    @SerializedName("strInstructionsIT")
    val instructionIT : String = instructionIT

    @SerializedName("strInstructionsZH-HANS")
    val instructionZH_HANS : String = instructionZH_HANS

    @SerializedName("strInstructionsZH-HANT")
    val instructionZH_HANT : String = instructionZH_HANT

    @SerializedName("strDrinkThumb")
    val drinkThumb : String = drinkThumb

    @SerializedName("strIngredient1")
    val i1 : String = i1

    @SerializedName("strIngredient2")
    val i2 : String = i2

    @SerializedName("strIngredient3")
    val i3 : String = i3

    @SerializedName("strIngredient4")
    val i4 : String = i4

    @SerializedName("strIngredient5")
    val i5 : String = i5

    @SerializedName("strIngredient6")
    val i6 : String = i6

    @SerializedName("strIngredient7")
    val i7 : String = i7

    @SerializedName("strIngredient8")
    val i8 : String = i8

    @SerializedName("strIngredient9")
    val i9 : String = i9

    @SerializedName("strIngredient10")
    val i10 : String = i10

    @SerializedName("strIngredient11")
    val i11 : String = i11

    @SerializedName("strIngredient12")
    val i12 : String = i12

    @SerializedName("strIngredient13")
    val i13 : String = i13

    @SerializedName("strIngredient14")
    val i14 : String = i14

    @SerializedName("strIngredient15")
    val i15 : String = i15

    @SerializedName("strMeasure1")
    val m1 : String = m1

    @SerializedName("strMeasure2")
    val m2 : String = m2

    @SerializedName("strMeasure3")
    val m3 : String = m3

    @SerializedName("strMeasure4")
    val m4 : String = m4

    @SerializedName("strMeasure5")
    val m5 : String = m5

    @SerializedName("strMeasure6")
    val m6 : String = m6

    @SerializedName("strMeasure7")
    val m7 : String = m7

    @SerializedName("strMeasure8")
    val m8 : String = m8

    @SerializedName("strMeasure9")
    val m9 : String = m9

    @SerializedName("strMeasure10")
    val m10 : String = m10

    @SerializedName("strMeasure11")
    val m11 : String = m11

    @SerializedName("strMeasure12")
    val m12 : String = m12

    @SerializedName("strMeasure13")
    val m13 : String = m13

    @SerializedName("strMeasure14")
    val m14 : String = m14

    @SerializedName("strMeasure15")
    val m15 : String = m15

    @SerializedName("strImageSource")
    val imageSource : String = imageSource

    @SerializedName("strImageAttribution")
    val imageAttribution : String = imageAttribution

    @SerializedName("strCreativeCommonsConfirmed")
    val creativeCommonsConfirmed : String = creativeCommonsConfirmed

    @SerializedName("dateModified")
    val dateModified : String = dateModified



    fun  getInstructionsMap() : HashMap<String, String> {
        var instructions : HashMap<String, String> = HashMap<String, String>()
        instructions.put("en", instructionEN)
        instructions.put("de", instructionDE)
        instructions.put("fr", instructionFR)
        instructions.put("it", instructionIT)
        instructions.put("es", instructionES)
        instructions.put("zh_hans", instructionZH_HANS)
        instructions.put("zh_hant", instructionZH_HANT)
        return instructions
    }

    fun  getIngredientsMap() : ListIngredients {
        var ingredients : HashMap<String, String> = HashMap<String, String>()
        var listIngredients : ArrayList<String> = arrayListOf(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15)
        var listMeasures : ArrayList<String> = arrayListOf(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15)

        for (i in 0..listIngredients.size-1) {
            if (listIngredients[i] != null) {
                ingredients.put(listIngredients[i], listMeasures[i])

            }
        }
        Log.i("SHOPPING", "10")

        return ListIngredients(ingredients)
    }

}

class CocktailDetailResponse(
    @SerializedName("drinks") val drinks: List <CocktailDetail>
)