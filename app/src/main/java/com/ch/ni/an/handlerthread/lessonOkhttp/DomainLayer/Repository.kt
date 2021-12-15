package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import android.util.Log
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.okHttp.OkHttp
import com.ch.ni.an.handlerthread.lessonOkhttp.Extensions.toUser
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException


class Repository : FetchListUsers, FetchUserById, NewPost, UpdatePost, DeletePost {

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

    override fun fetchUserById(id :Int) :User {
        TODO("Not yet implemented")
    }

    override fun newPost(postData :PostModel) :Boolean {
        TODO("Not yet implemented")
    }

    override fun updatePost(postData :PostModel) :Boolean {
        TODO("Not yet implemented")
    }


}