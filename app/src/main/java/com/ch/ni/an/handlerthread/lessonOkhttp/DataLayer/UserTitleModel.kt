package com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserTitleModel(
    @SerialName("title")
    private val titlePost : String
) {
}