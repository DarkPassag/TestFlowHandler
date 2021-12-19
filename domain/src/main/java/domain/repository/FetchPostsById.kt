package domain.repository

import domain.models.PostDomainModel


interface FetchPostsById{

   suspend fun getPosts(id:Int): List<PostDomainModel>
}