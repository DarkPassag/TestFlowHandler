package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import android.util.Log
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.okHttp.OkHttp
import com.ch.ni.an.handlerthread.lessonOkhttp.Extensions.toUser
import com.ch.ni.an.handlerthread.trash.model.Post
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException


class Repository : FetchListUsers, FetchPostsById, NewPost, UpdatePost, DeletePost, PatchPost {

    private val okHttp = OkHttp()


    override fun deletePost(id :Int) :Boolean {
        TODO("Not yet implemented")
    }

    override fun fetchListOfUsers() :List<User> {
        okHttp.getListOfUsers().clone().execute().use { response ->
            if(!response.isSuccessful) throw IOException(response.message)
            val stringResponse = response.body!!.string()
            val listModelUsers = Json.decodeFromString<List<UserModel>>(stringResponse)
            return listModelUsers.map { UserModel ->
                UserModel.toUser()
            }
        }

    }

    override fun getPosts(id :Int) :List<PostModel> {
      okHttp.getListPostsByUser(id).clone().execute().use { response ->
          if(!response.isSuccessful) throw IOException(response.message)
          val stringResponse = response.body!!.string()
          val listPostsModel = Json.decodeFromString<List<PostModel>>(stringResponse)
          return listPostsModel
      }
    }

    override fun newPost(postData :PostModel) :PostModel {
        okHttp.addNewPost(postData).clone().execute().use { response ->
            if(!response.isSuccessful) throw  IOException(response.message)
            val stringResponseBody = response.body!!.string()
            val format = Json { ignoreUnknownKeys = true }
            return format.decodeFromString<PostModel>(stringResponseBody)


        }

    }

    override fun updatePost(postData :PostModel) :PostModel {
        okHttp.updatePost(postData).clone().execute().use { response ->
            if(!response.isSuccessful) throw IOException(response.message)
            val stringResponseBody = response.body!!.string()
            return Json.decodeFromString<PostModel>(stringResponseBody)

        }
    }

    override fun patchPost(id: Int, title: String): PostModel {
        okHttp.patchPost(id, title).clone().execute().use {
            if(!it.isSuccessful) throw IOException(it.message)
            val responseString = it.body!!.string()
            return Json.decodeFromString(responseString)
        }
    }


}