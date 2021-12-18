package com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.retrofit

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.okHttp.BASE_URL
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.okHttp.OkHttp
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitClient {

    private var retrofit: Retrofit? = null

    private val loggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ContentTypeInterceptor())
        .build()

    private val contentType = "application/json".toMediaType()

    fun getClient(): Retrofit{

        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(Json.asConverterFactory(contentType))
                .client(client)
                .build()
        }
        return retrofit!!
    }

}