package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import android.util.Log
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserTitleModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.okHttp.OkHttp
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.retrofit.Common
import com.ch.ni.an.handlerthread.lessonOkhttp.Extensions.toUser
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import kotlin.random.Random


class Repository : FetchListUsers, FetchPostsById, NewPost, UpdatePost, DeletePost, PatchPost {

    private val okHttp = OkHttp()
    private val service = Common.service


    override suspend fun deletePost(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun fetchListOfUsers(): List<User> {
        val boolean = Random.nextBoolean()
        return if (boolean) fetchListUsersByOkHttp()
        else fetchListUsersByRetrofit()
    }

    private fun fetchListUsersByOkHttp(): List<User> {
        okHttp.getListOfUsers().clone().execute().use { response ->
            if (!response.isSuccessful) throw IOException(response.message)
            val stringResponse = response.body!!.string()
            Log.e("TAG", "okHttp")
            val listModelUsers = Json.decodeFromString<List<UserModel>>(stringResponse)
            return listModelUsers.map { UserModel ->
                UserModel.toUser()
            }
        }
    }

    private suspend fun fetchListUsersByRetrofit(): List<User> {
        val response = service.getListUsers()
        if (!response.isSuccessful) throw IOException(response.message())
        Log.e("TAG", "retrofit")
        return response.body()!!.map { it.toUser() }
    }

    override suspend fun getPosts(id: Int): List<PostModel> {
        val boolean = Random.nextBoolean()
        return if (boolean) {
            getPostsByOkHttp(id)
        } else {
            getPostsByRetrofit(id)
        }
    }

    private fun getPostsByOkHttp(id: Int): List<PostModel> {
        okHttp.getListPostsByUser(id).clone().execute().use { response ->
            if (!response.isSuccessful) throw IOException(response.message)
            val stringResponse = response.body!!.string()
            val listPostsModel = Json.decodeFromString<List<PostModel>>(stringResponse)
            Log.e("TAG", "okHttpGetPosts")
            return listPostsModel
        }
    }

    private suspend fun getPostsByRetrofit(id: Int): List<PostModel> {
        val response = service.getPostsByUser(id)
        if (!response.isSuccessful) throw IOException(response.message())
        Log.e("TAG", "retrofitGetPosts")
        return response.body()!!
    }

    override suspend fun newPost(postData: PostModel): PostModel {
        val boolean = Random.nextBoolean()
        return if (boolean) {
            newPostByOkHttp(postData)
        } else {
            newPostByRetrofit(postData)
        }
    }

    private fun newPostByOkHttp(postData: PostModel): PostModel {
        okHttp.addNewPost(postData).clone().execute().use { response ->
            if (!response.isSuccessful) throw  IOException(response.message)
            val stringResponseBody = response.body!!.string()
            val format = Json { ignoreUnknownKeys = true }
            Log.e("TAG", "okHttpPost")
            return format.decodeFromString<PostModel>(stringResponseBody)
        }
    }

    private suspend fun newPostByRetrofit(postData: PostModel): PostModel {
        val response = service.addNewPost(postData)
        if (!response.isSuccessful) throw IOException(response.message())
        Log.e("TAG", "retrofitPost")
        return response.body()!!
    }

    override suspend fun updatePost(postData: PostModel): PostModel {
        val boolean = Random.nextBoolean()
        return if (boolean) {
            updatePostByOkHttp(postData)
        } else {
            updatePostByRetrofit(postData)
        }
    }

    private fun updatePostByOkHttp(postData: PostModel): PostModel {
        okHttp.updatePost(postData).clone().execute().use { response ->
            if (!response.isSuccessful) throw IOException(response.message)
            val stringResponseBody = response.body!!.string()
            Log.e("TAG", "okHttpUpdatePut")
            return Json.decodeFromString<PostModel>(stringResponseBody)
        }
    }

    private suspend fun updatePostByRetrofit(postData: PostModel): PostModel {
        val response = service.updatePostByUser(postData.userId, postData)
        if (!response.isSuccessful) throw IOException(response.message())
        Log.e("TAG", "retrofitUpdatePut")
        return response.body()!!
    }

    override suspend fun patchPost(id: Int, title: String): PostModel {
        val boolean = Random.nextBoolean()
        return if (boolean) {
            patchPostByOkHttp(id, title)
        } else {
            patchPostsByRetrofit(id, title)
        }
    }

    private fun patchPostByOkHttp(id: Int, title: String): PostModel {
        okHttp.patchPost(id, title).clone().execute().use {
            if (!it.isSuccessful) throw IOException(it.message)
            val responseString = it.body!!.string()
            Log.e("TAG", "okHttpPatch")
            return Json.decodeFromString(responseString)
        }
    }

    private suspend fun patchPostsByRetrofit(id: Int, title: String): PostModel {
        val userTitle = UserTitleModel(title)
        val response = service.patchPost(id,userTitle)
        Log.e("TAG", "retrofitPatch")
        return response.body()!!
    }


}