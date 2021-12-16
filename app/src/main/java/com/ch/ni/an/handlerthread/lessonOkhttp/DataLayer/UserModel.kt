package com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressModel,
    val phone: String,
    val website: String,
    val company: CompanyData
) {}

@Serializable
@Parcelize
data class AddressModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoModel
) : Parcelable

@Serializable
@Parcelize
data class GeoModel(
    val lat: String,
    val lng: String
) : Parcelable

@Serializable
@Parcelize
data class CompanyData(
    val name: String,
    val catchPhrase: String,
    val bs: String
) : Parcelable