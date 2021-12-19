package domain.repository

import domain.models.UserDomainModel

interface FetchListUsers {

   suspend fun fetchListOfUsers(): List<UserDomainModel>
}