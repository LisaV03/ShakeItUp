package com.example.shakeitup.core.service

import android.util.Log
import com.example.shakeitup.core.model.CocktailDetail
import com.example.shakeitup.core.model.CocktailDetailResponse
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL

class RandomCocktailFetcher {
    companion object {
        fun fetchRandomCocktail(success: (CocktailDetail) -> Unit, failure: () -> Unit) {
                val client = OkHttpClient()
                val url = URL("https://www.thecocktaildb.com/api/json/v1/1/random.php")
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
                                val cocktailDetailResponse: CocktailDetailResponse = gson.fromJson(responseBody, CocktailDetailResponse::class.java)
                                val cocktailDetailList: List <CocktailDetail> = cocktailDetailResponse.drinks
                                val cocktailDetail : CocktailDetail = cocktailDetailList[0]

                                success(cocktailDetail)
                            }
                            else {
                                failure()
                            }
                        }


                    })
            }
        }
}