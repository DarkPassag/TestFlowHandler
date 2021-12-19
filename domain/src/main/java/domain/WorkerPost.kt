package domain

import domain.models.PostDomainModel
import domain.repository.PostsRepository

class WorkerPost(
    private val userRepository: PostsRepository
) {

    suspend fun addNewPost(postModel: PostDomainModel): PostDomainModel {
        return userRepository.newPost(postModel)
    }

    suspend fun fetchPosts(id: Int): List<PostDomainModel> {
        return userRepository.getPosts(id)
    }

    suspend fun updatePost(id: Int, title: String): PostDomainModel {
        return userRepository.patchPost(id, title)
    }

    suspend fun updatePost(post: PostDomainModel): PostDomainModel {
        return userRepository.updatePost(post)
    }
}