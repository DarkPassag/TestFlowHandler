package domain

import domain.models.PostDomainModel
import domain.repository.FetchPostsById


class FetchPostsByUserId(
    private val userRepository: FetchPostsById
) {

    suspend fun fetchPosts(id: Int): List<PostDomainModel> {
        return userRepository.getPosts(id)
    }


}