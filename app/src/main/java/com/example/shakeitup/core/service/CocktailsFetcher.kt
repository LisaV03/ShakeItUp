package com.example.shakeitup.core.service

import android.util.Log
import com.example.shakeitup.core.model.Categories
import com.example.shakeitup.core.model.CategoryResponse
import com.example.shakeitup.core.model.Cocktail
import com.example.shakeitup.core.model.CocktailsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL

class CocktailsFetcher {
    companion object {
        fun fetchCocktails(name: String, success: (ArrayList<Cocktail>) -> Unit, failure: () -> Unit) {

            val client = OkHttpClient()
            val url = URL("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c="+name)
            val request = Request.Builder().url(url).build()
            // Request
            client
                .newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.i("OKHTTP", "OnFailure: ${e.localizedMessage}")
                        failure()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.i("OKHTTP", "OnSuccess")
                        if (response.isSuccessful) {
                            val responseBody = response.body?.string()
                            Log.i("OKHTTP", responseBody ?: "Empty")

                            // Deserialize the category in an List of Categories
                            val gson = Gson()
                            val cocktailResponseType = object : TypeToken<CocktailsResponse>() {}.type
                            val cocktailResponse: CocktailsResponse = gson.fromJson(responseBody, cocktailResponseType)
                            val cocktailList: List<Cocktail> = cocktailResponse.drinks

                            success(cocktailList as ArrayList<Cocktail>)
                        }
                        else {
                           failure()
                        }
                    }

                    
                })
        }
    }
}
