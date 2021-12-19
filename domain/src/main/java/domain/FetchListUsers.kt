package domain

import domain.models.UserDomainModel
import domain.repository.FetchListUsers



class FetchListUsers(
    private val userRepository: FetchListUsers
) {

    suspend fun fetchUsers(): List<UserDomainModel>{
        return userRepository.fetchListOfUsers()
    }
}