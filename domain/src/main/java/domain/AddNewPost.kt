package domain

import domain.models.PostDomainModel
import domain.repository.NewPost


open class AddNewPost(
    private val userRepository: NewPost

){

    suspend fun addNewPost(postModel: PostDomainModel): PostDomainModel {
       return userRepository.newPost(postModel)
    }
}