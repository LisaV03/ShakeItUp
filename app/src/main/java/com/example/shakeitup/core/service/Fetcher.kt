package com.example.shakeitup.core.service

import android.util.Log
import com.example.shakeitup.core.model.ResponseWrapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

abstract class Fetcher {
    val client = OkHttpClient()

    abstract fun getUrl(): String

    inline fun <reified T> fetchData(crossinline success: (ArrayList<T>) -> Unit, crossinline failure: () -> Unit) {
        val url = getUrl()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("OKHTTP", "OnFailure: ${e.localizedMessage}")
                failure()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("OKHTTP", "OnSuccess")
                if (response.isSuccessful) {
                    handleSuccessfulResponse(response.body?.string(), success, failure)
                } else {
                    failure()
                }
            }
        })
    }

    inline fun <reified T> handleSuccessfulResponse(responseBody: String?, success: (ArrayList<T>) -> Unit, failure: () -> Unit) {
        val gson = Gson()
        val type = object : TypeToken<ResponseWrapper<T>>() {}.type
        try {
            val response: ResponseWrapper<T> = gson.fromJson(responseBody, type)
            val resultList: List<T> = response.drinks
            success(resultList as ArrayList<T>)
        } catch (e: Exception) {
            Log.e("Fetcher", "Error parsing response: ${e.message}")
            failure()
        }
    }
}
