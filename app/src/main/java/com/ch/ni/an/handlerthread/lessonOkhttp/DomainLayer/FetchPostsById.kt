package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel


interface FetchPostsById {

    fun getPosts(id:Int): List<PostModel>
}