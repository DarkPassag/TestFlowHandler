package com.ch.ni.an.handlerthread.trash.model.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("generate_insult.php?lang=en&type=plainText")
   suspend fun fetchAny(): Response<String>
}

object Common{

    val retrofit = RetrofitClient.getClient().create(RetrofitService::class.java)
}