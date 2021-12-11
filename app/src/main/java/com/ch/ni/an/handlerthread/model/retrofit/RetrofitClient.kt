package com.ch.ni.an.handlerthread.model.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


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
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}