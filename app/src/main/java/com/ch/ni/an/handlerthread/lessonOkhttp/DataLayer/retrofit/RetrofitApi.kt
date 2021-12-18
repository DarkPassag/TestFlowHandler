package com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.retrofit

import android.view.ViewDebug
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserTitleModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.lang.IndexOutOfBoundsException

interface RetrofitApi {


    @GET("users")
    suspend fun getListUsers(): Response<List<UserModel>>

    @GET("posts")
    suspend fun getPostsByUser(
        @Query("userId") id: Int
    ): Response<List<PostModel>>

    @Headers(
        "Content-type: application/json; charset=UTF-8"
    )
    @POST("posts")
    suspend fun addNewPost(
        @Body postModel: PostModel
    ): Response<PostModel>

    @Headers(
        "Content-type: application/json; charset=UTF-8"
    )
    @PUT("posts/{userId}")
    suspend fun updatePostByUser(
        @Path("userId") id: Int,
        @Body postModel: PostModel
    ): Response<PostModel>

    @Headers(
        "Content-type: application/json; charset=UTF-8"
    )
    @PATCH("posts/{id}/")
    suspend fun patchPost(
        @Path("id") id: Int,
        @Body body: UserTitleModel
    ): Response<PostModel>
}


object Common{

    val service = RetrofitClient.getClient().create(RetrofitApi::class.java)
}