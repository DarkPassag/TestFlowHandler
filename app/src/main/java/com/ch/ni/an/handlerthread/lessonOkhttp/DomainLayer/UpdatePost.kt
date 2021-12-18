package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel

interface UpdatePost {

   suspend fun updatePost(postData :PostModel): PostModel
}