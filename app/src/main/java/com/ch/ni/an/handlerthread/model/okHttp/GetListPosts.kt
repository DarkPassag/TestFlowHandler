package com.ch.ni.an.handlerthread.model.okHttp

import com.ch.ni.an.handlerthread.model.Post

interface GetListPosts {

    suspend fun getListPosts(): List<Post>
}