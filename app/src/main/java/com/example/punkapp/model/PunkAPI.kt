package com.example.punkapp.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object PunkAPI {
    val gson = Gson()
    val client = OkHttpClient()

    fun loadActivity(): PunkBean {
        val json: String = sendGet("https://api.punkapi.com/v2/beers")
        val data : PunkBean = gson.fromJson(json, PunkBean::class.java)

        return data
    }

    fun sendGet(url: String): String {
        println("url : $url")

        val request = Request.Builder().url(url).build()

        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
}