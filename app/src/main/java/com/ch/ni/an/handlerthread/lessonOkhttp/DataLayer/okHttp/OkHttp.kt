package com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.okHttp

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Call
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


    private val body = Json.encodeToString(PostModel("New Post"," Today i send post request", 1))

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



}