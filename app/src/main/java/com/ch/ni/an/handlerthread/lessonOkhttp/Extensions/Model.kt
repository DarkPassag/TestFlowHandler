package com.ch.ni.an.handlerthread.lessonOkhttp.Extensions

import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserUiLayer

fun UserModel.toUser() :User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address,
        phone = phone,
        website = website,
        company = company
    )
}

fun User.toUserUiLayer(): UserUiLayer{
    val id = this.id
    val name = this.name + " "+ this.username
    val cAddress = this.address
    val address =  cAddress.city +" "+ cAddress.street+" "+cAddress.suite
    val phone = this.phone
    val email = this.email
    val cCompany = this.company
    val company = "${cCompany.name}\n${cCompany.catchPhrase}"
    val website = this.website

    return UserUiLayer(
       id ,name, address, phone, email, company, website
    )
}