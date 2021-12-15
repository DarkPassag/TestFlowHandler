package com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer

import kotlinx.serialization.Serializable


@Serializable
data class PostModel(
    val title: String,
    val body: String,
    val userId: Int
) {}