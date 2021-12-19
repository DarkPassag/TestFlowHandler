package domain.repository

import domain.models.PostDomainModel


interface PatchPost : UserRepository {

   suspend fun patchPost(id:Int, title: String): PostDomainModel
}