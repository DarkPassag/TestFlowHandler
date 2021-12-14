package com.ch.ni.an.handlerthread.model.retrofit

import com.ch.ni.an.handlerthread.model.User
import com.ch.ni.an.handlerthread.model.UserModel

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