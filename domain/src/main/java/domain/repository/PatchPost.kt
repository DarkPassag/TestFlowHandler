package domain.repository

import domain.models.PostDomainModel


interface PatchPost {

   suspend fun patchPost(id:Int, title: String): PostDomainModel
}