package com.ch.ni.an.handlerthread.lessonOkhttp.Extensions

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserModel

fun UserModel.toUser() :User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address,
        phone = phone,
        website = website,
        company = company
    )
}