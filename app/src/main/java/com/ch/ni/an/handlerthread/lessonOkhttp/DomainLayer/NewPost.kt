package com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel

interface NewPost {

   suspend fun newPost(postData :PostModel): PostModel
}