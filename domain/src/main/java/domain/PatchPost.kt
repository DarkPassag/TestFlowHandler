package domain


import domain.models.PostDomainModel
import domain.repository.PatchPost


class PatchPost(
    private val userRepository: PatchPost
) {

    suspend fun updatePost(id: Int, title: String): PostDomainModel {
        return userRepository.patchPost(id, title)
    }
}