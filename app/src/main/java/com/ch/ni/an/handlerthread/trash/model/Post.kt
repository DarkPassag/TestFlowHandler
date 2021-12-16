package com.ch.ni.an.handlerthread.trash.model

import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val completed: Boolean?
)
