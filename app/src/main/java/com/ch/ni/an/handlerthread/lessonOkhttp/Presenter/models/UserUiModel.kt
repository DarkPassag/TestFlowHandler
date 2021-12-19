package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserUiModel(
    val id: Int,
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val company: String,
    val website: String
): Parcelable
