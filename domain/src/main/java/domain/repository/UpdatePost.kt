package domain.repository


import domain.models.PostDomainModel

interface UpdatePost : UserRepository {

   suspend fun updatePost(post : PostDomainModel): PostDomainModel
}