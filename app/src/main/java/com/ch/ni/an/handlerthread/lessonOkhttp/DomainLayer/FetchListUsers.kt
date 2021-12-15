package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User

interface FetchListUsers {

    fun fetchListOfUsers(): List<User>
}