package domain.repository

interface DeletePost : UserRepository {

    suspend fun deletePost(id: Int): Boolean
}