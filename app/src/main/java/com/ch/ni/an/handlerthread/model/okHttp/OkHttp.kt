package com.ch.ni.an.handlerthread.model.okHttp

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor


object OkHttp {

    private val client :OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private val request = Request.Builder()
        .url("https://evilinsult.com/generate_insult.php?lang=en&type=plainText")
        .build()

    val response = client.newCall(request)
}