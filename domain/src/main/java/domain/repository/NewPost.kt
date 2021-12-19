package domain.repository

import domain.models.PostDomainModel


interface NewPost : UserRepository {

   suspend fun newPost(postData : PostDomainModel): PostDomainModel
}