package com.example.punkapp.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object PunkAPI {
    private val gson = Gson()
    private val client = OkHttpClient()

    fun loadBeers(): PunkBean {
        val json: String = sendGet("https://api.punkapi.com/v2/beers")
        return gson.fromJson(json, PunkBean::class.java)
    }

    private fun sendGet(url: String): String {
        println("url : $url")

        val request = Request.Builder().url(url).build()

        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Server Status: ${it.code}")
            }
            it.body.string()
        }
    }
}