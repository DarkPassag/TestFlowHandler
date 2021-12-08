package com.ch.ni.an.handlerthread.retrofit

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl("https://evilinsult.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}