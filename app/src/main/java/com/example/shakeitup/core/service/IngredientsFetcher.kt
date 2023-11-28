package com.example.shakeitup.core.service

import android.util.Log
import com.example.shakeitup.core.model.Categories
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL

class IngredientsFetcher {
    companion object {

        private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/list.php?"
        fun fetchIngredients(success: (ArrayList<String>) -> Unit, failure: () -> Unit) {
            val client = OkHttpClient()
            val urlIngredients = URL(BASE_URL + "i=list")
            val request = Request.Builder().url(urlIngredients).build()

            client.newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.i("OKHTTP", "OnFailure: ${e.localizedMessage}")
                        call.cancel()
                        failure()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val response: Response = client.newCall(request).execute()
                        val gson = Gson()
                        val responseBody = response.body?.string()

                        val jsonObject = gson.fromJson(responseBody, JsonObject::class.java)
                        val drinksArray =
                            jsonObject.getAsJsonArray("drinks") // look at the API response to understand ...

                        val ingredientsArray = ArrayList<String>()
                        for (ele in drinksArray) {
                            val elementObject: JsonObject = ele.asJsonObject
                            val ingredient = elementObject.get("strIngredient1").asString
                            ingredientsArray.add(ingredient)
                        }
                        Log.i("IngredientFetcher", "Fetch list: $ingredientsArray")
                        success(ingredientsArray)
                    }
                })
        }
    }
}