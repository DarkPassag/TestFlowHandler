package domain.repository

import domain.models.PostDomainModel


interface NewPost {

   suspend fun newPost(postData : PostDomainModel): PostDomainModel
}