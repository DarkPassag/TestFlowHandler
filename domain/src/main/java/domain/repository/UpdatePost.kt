package domain.repository


import domain.models.PostDomainModel

interface UpdatePost {

   suspend fun updatePost(post : PostDomainModel): PostDomainModel
}