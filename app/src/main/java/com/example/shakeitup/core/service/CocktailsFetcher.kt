package com.example.shakeitup.core.service

import android.annotation.SuppressLint
import android.util.Log
import com.example.shakeitup.core.model.Categories
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
        //Type 0 is for categories research
        //Type 1 for ingredients research

        private val client = OkHttpClient()

        fun fetchCocktails(name: String, type: Int, success: (ArrayList<Cocktail>) -> Unit, failure: () -> Unit) {

            val url : URL
            if (type == 0){
                url = URL("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c="+name)
            }
            else {
                url = URL("https://www.thecocktaildb.com/api/json/v1/1/filter.php?i="+name)
            }

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

        private fun executeRequest(url: URL): Response {
            val request = Request.Builder().url(url).build()
            return client.newCall(request).execute()
        }

        private fun handleSuccessfulResponse(response: Response): List<Cocktail>{
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                Log.i("OKHTTP", responseBody ?: "Empty")

                // Deserialize the category in an List of Categories
                val gson = Gson()
                val cocktailResponseType =
                    object : TypeToken<CocktailsResponse>() {}.type
                val cocktailResponse: CocktailsResponse =
                    gson.fromJson(responseBody, cocktailResponseType)
                return cocktailResponse.drinks
            }else{
                return emptyList()
            }
        }

        @SuppressLint("SuspiciousIndentation")
        fun fetchAllCocktails(success: (ArrayList<Cocktail>) -> Unit, failure: () -> Unit) {
            val categoriesFetcher = CategoriesFetcher()
            categoriesFetcher.fetchData<Categories>(
                success = { categories ->
                    val allCocktails = mutableListOf<Cocktail>()
                    for(category in categories){
                        val url = URL("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c="+category.name)
                        val response = executeRequest(url)
                        val cocktailList = handleSuccessfulResponse(response)
                            allCocktails.addAll(cocktailList)
                    }
                    success(allCocktails as ArrayList<Cocktail>)
                },
                failure = {
                    // Handle failure for fetching categories
                    Log.e("OKHTTP","We've got a when fetching the categories ...")
                    failure()
                }
            )
        }
    }
}