package com.example.shakeitup.core.service

import android.util.Log
import com.example.shakeitup.core.model.Categories
import com.example.shakeitup.core.model.CategoryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL

class CategoriesFetcher {
    companion object {
        fun fetchCategories(success: (ArrayList<Categories>) -> Unit, failure: () -> Unit) {

            val client = OkHttpClient()
            val url = URL("https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list")
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
                            val categoryResponseType = object : TypeToken<CategoryResponse>() {}.type
                            val categoryResponse: CategoryResponse = gson.fromJson(responseBody, categoryResponseType)
                            val categoryList: List<Categories> = categoryResponse.drinks

                            success(categoryList as ArrayList<Categories>)
                        }
                        else {
                           failure()
                        }
                    }

                    
                })
        }
    }
}
