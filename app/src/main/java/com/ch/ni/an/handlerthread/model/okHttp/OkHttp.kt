package com.ch.ni.an.handlerthread.model.okHttp

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


object OkHttp {

    private val client :OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)



    private val request = Request.Builder()
        .url("https://jsonplaceholder.typicode.com/todos")
        .build()

    private val requestUsers = Request.Builder()
        .url("https://jsonplaceholder.typicode.com/users")
        .build()

    val response = client.newCall(request)
    val responseUsers = client.newCall(requestUsers)
}

