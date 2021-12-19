package data.retrofit


import data.PostModel
import data.UserModel
import data.UserTitleModel
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {


    @GET("users")
    suspend fun getListUsers(): Response<List<UserModel>>

    @GET("posts")
    suspend fun getPostsByUser(
        @Query("userId") id: Int
    ): Response<List<PostModel>>


    @POST("posts")
    suspend fun addNewPost(
        @Body postModel: PostModel
    ): Response<PostModel>


    @PUT("posts/{userId}")
    suspend fun updatePostByUser(
        @Path("userId") id: Int,
        @Body postModel: PostModel
    ): Response<PostModel>


    @PATCH("posts/{id}/")
    suspend fun patchPost(
        @Path("id") id: Int,
        @Body body: UserTitleModel
    ): Response<PostModel>
}


object Common{

    val service = RetrofitClient.getClient().create(RetrofitApi::class.java)
}