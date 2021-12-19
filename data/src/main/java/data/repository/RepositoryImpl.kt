package data.repository


import data.PostModel
import data.UserModel
import data.UserTitleModel
import data.okHttp.OkHttp
import data.retrofit.RetrofitApi
import data.extensions.toPostDomainModel
import data.extensions.toPostModel
import data.extensions.toUserDomainModel
import domain.models.PostDomainModel
import domain.models.UserDomainModel
import domain.repository.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException
import kotlin.random.Random


class RepositoryImpl(
    private val okHttp: OkHttp,
    private val service: RetrofitApi
) : DeletePost,  FetchListUsers, PostsRepository  {


    val boolean = Random.nextBoolean()

    override suspend fun deletePost(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun fetchListOfUsers(): List<UserDomainModel> {
        return if (boolean) fetchListUsersByOkHttp()
        else fetchListUsersByRetrofit()
    }

    private fun fetchListUsersByOkHttp(): List<UserDomainModel> {
        okHttp.getListOfUsers().clone().execute().use { response ->
            if (!response.isSuccessful) throw IOException(response.message)
            val stringResponse = response.body!!.string()
            val listModelUsers = Json.decodeFromString<List<UserModel>>(stringResponse)
            return listModelUsers.map { it.toUserDomainModel() }
        }
    }

    private suspend fun fetchListUsersByRetrofit(): List<UserDomainModel> {
        val response = service.getListUsers()
        if (!response.isSuccessful) throw IOException(response.message())
        return response.body()!!.map { it.toUserDomainModel() }
    }

    override suspend fun getPosts(id: Int): List<PostDomainModel> {
        return if (boolean) {
            getPostsByOkHttp(id)
        } else {
            getPostsByRetrofit(id)
        }
    }

    private fun getPostsByOkHttp(id: Int): List<PostDomainModel> {
        okHttp.getListPostsByUser(id).clone().execute().use { response ->
            if (!response.isSuccessful) throw IOException(response.message)
            val stringResponse = response.body!!.string()
            val listPostsModel = Json.decodeFromString<List<PostModel>>(stringResponse)
            return listPostsModel.map { it.toPostDomainModel() }
        }
    }

    private suspend fun getPostsByRetrofit(id: Int): List<PostDomainModel> {
        val response = service.getPostsByUser(id)
        if (!response.isSuccessful) throw IOException(response.message())
        return response.body()!!.map { it.toPostDomainModel()}
    }

    override suspend fun newPost(postData: PostDomainModel): PostDomainModel {
      return if(boolean){
          newPostByOkHttp(postData.toPostModel())
      } else {
          newPostByRetrofit(postData.toPostModel())
      }
    }

    private fun newPostByOkHttp(postData: PostModel): PostDomainModel {
        okHttp.addNewPost(postData).clone().execute().use { response ->
            if (!response.isSuccessful) throw  IOException(response.message)
            val stringResponseBody = response.body!!.string()
            val format = Json { ignoreUnknownKeys = true }
            return format.decodeFromString<PostModel>(stringResponseBody).toPostDomainModel()
        }
    }

    private suspend fun newPostByRetrofit(postData: PostModel): PostDomainModel {
        val response = service.addNewPost(postData)
        if (!response.isSuccessful) throw IOException(response.message())
        return response.body()!!.toPostDomainModel()
    }

    override suspend fun updatePost(postData: PostDomainModel): PostDomainModel {
        return if(boolean){
            updatePostByOkHttp(postData.toPostModel())
        } else{
            updatePostByRetrofit(postData.toPostModel())
        }
    }

    private fun updatePostByOkHttp(postData: PostModel): PostDomainModel {
        okHttp.updatePost(postData).clone().execute().use { response ->
            if (!response.isSuccessful) throw IOException(response.message)
            val stringResponseBody = response.body!!.string()
            return Json.decodeFromString<PostModel>(stringResponseBody).toPostDomainModel()
        }
    }

    private suspend fun updatePostByRetrofit(postData: PostModel): PostDomainModel {
        val response = service.updatePostByUser(postData.userId, postData)
        if (!response.isSuccessful) throw IOException(response.message())
        return response.body()!!.toPostDomainModel()
    }

    override suspend fun patchPost(id: Int, title: String): PostDomainModel {
        return if (boolean) {
            patchPostByOkHttp(id, title)
        } else {
            patchPostsByRetrofit(id, title)
        }

    }

    private fun patchPostByOkHttp(id: Int, title: String): PostDomainModel {
        okHttp.patchPost(id, title).clone().execute().use {
            if (!it.isSuccessful) throw IOException(it.message)
            val responseString = it.body!!.string()
            return Json.decodeFromString<PostModel>(responseString).toPostDomainModel()
        }
    }

    private suspend fun patchPostsByRetrofit(id: Int, title: String): PostDomainModel {
        val userTitle = UserTitleModel(title)
        val response = service.patchPost(id, userTitle)
        return response.body()!!.toPostDomainModel()
    }


}