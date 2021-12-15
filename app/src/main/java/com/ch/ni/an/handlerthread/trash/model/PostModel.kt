package com.ch.ni.an.handlerthread.trash.model

import kotlinx.serialization.Serializable


@Serializable
data class PostModel(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val completed: Boolean?
) {


}

fun PostModel.toPost():Post{
    return Post(
        this.userId,
        this.id,
        this.title,
        this.completed)
}

