package domain


import domain.models.PostDomainModel
import domain.repository.UpdatePost


class UpdatePost(
    private val userRepository: UpdatePost
) {

    suspend fun updatePost(post: PostDomainModel): PostDomainModel{
        return userRepository.updatePost(post)
    }
}