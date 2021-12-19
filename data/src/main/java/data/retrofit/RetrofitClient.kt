package data.retrofit

import data.okHttp.BASE_URL
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null

    private val loggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
//        .addInterceptor(ContentTypeInterceptor())
        .build()

    private val contentType = "application/json".toMediaType()

    fun getClient(): Retrofit{

        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(Json.asConverterFactory(contentType))
                .client(client)
                .build()
        }
        return retrofit!!
    }

}