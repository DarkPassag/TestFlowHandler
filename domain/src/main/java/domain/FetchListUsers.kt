package domain

import domain.models.UserDomainModel
import domain.repository.FetchListUsers
import domain.repository.Repository


class FetchListUsers(
    private val userRepository: Repository
) {

    suspend fun fetchUsers(): List<UserDomainModel>{
        return userRepository.fetchListOfUsers()
    }
}