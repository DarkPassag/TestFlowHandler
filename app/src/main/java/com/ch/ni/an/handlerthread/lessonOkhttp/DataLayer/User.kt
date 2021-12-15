package com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressModel,
    val phone: String,
    val website: String,
    val company: CompanyData
) {}