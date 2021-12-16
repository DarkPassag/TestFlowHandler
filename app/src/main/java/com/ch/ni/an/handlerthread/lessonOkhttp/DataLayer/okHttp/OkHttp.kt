package com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.okHttp

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor

class OkHttp {

    private val logging =
        HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)


    private val client =
        OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()




    /**
    private val request = Request.Builder()
        .url("")
        .post(body.toRequestBody())
        .build()

    val newCall = client.newCall(request)
    */

    fun getListOfUsers(): Call {
        val getRequest = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/users")
            .build()

        return client.newCall(getRequest)
    }

    fun getListPostsByUser(id: Int): Call{
        val query = "https://jsonplaceholder.typicode.com/posts".toHttpUrl().newBuilder()
        query.addQueryParameter("userId", "$id")
        val requestPosts = Request.Builder()
            .url(query.build())
            .build()
        return client.newCall(requestPosts)
    }



}