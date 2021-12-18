package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

interface DeletePost {

   suspend fun deletePost(id:Int): Boolean
}