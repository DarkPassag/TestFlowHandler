package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel

interface PatchPost {

    fun patchPost(id:Int, title: String): PostModel
}