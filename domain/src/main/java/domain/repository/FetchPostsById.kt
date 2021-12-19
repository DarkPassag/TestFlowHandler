package domain.repository

import domain.models.PostDomainModel


interface FetchPostsById : UserRepository {

   suspend fun getPosts(id:Int): List<PostDomainModel>
}