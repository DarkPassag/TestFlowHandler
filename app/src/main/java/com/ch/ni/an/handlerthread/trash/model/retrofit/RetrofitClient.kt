package com.ch.ni.an.handlerthread.trash.model.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit



object RetrofitClient {

    private var retrofit: Retrofit? = null


    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    fun getClient(): Retrofit {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl("https://evilinsult.com/")
                .client(client)
                .build()
        }
        return retrofit!!
    }
}