package com.ch.ni.an.handlerthread.model.okHttp

import kotlinx.coroutines.flow.Flow

interface GetOkHttp {

    fun getOkhttp() :String

    suspend fun getOkHttp() :Flow<String>
}