package com.ch.ni.an.handlerthread.model

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
data class AddressModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoModel
) {}

@Serializable
data class GeoModel(
    val lat: String,
    val lng: String
)

@Serializable
data class CompanyData(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

