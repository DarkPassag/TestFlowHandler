package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User

interface FetchUserById {

    fun fetchUserById(id:Int): User
}