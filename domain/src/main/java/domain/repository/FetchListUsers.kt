package domain.repository

import domain.models.UserDomainModel

interface FetchListUsers : UserRepository {

   suspend fun fetchListOfUsers(): List<UserDomainModel>
}