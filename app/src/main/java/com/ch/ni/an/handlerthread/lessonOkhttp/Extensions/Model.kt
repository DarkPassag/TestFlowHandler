package com.ch.ni.an.handlerthread.lessonOkhttp.Extensions


import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.PostUiModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.UserUiModel
import domain.models.PostDomainModel
import domain.models.UserDomainModel


fun UserDomainModel.toUserUiModel(): UserUiModel {
    val id = this.id
    val name = this.name + " " + this.username
    val cAddress = this.address
    val address = cAddress.city + " " + cAddress.street + " " + cAddress.suite
    val phone = this.phone
    val email = this.email
    val cCompany = this.company
    val company = "${cCompany.name}\n${cCompany.catchPhrase}"
    val website = this.website

    return UserUiModel(
        id, name, address, phone, email, company, website
    )

}

fun PostDomainModel.toPostUiModel(): PostUiModel{
    return PostUiModel(id, title, body, userId)
}

fun PostUiModel.toPostDomainModel(): PostDomainModel{
    return PostDomainModel(id, title, body, userId)
}

