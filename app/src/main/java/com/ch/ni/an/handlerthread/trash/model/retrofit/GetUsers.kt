package com.ch.ni.an.handlerthread.trash.model.retrofit

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserModel

interface GetUsers {

    fun getListUsers(): List<User>
}

fun UserModel.toUser(): User{
    return User(
       id = id,
       name = name,
       username =  username,
       email =  email,
       address =  address,
       phone =  phone,
       website =  website,
       company =  company
    )
}