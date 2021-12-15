package com.ch.ni.an.handlerthread.trash.model.okHttp

import com.ch.ni.an.handlerthread.trash.model.Post

interface GetListPosts {

    suspend fun getListPosts(): List<Post>
}