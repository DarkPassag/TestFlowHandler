package domain.repository

interface DeletePost {

    suspend fun deletePost(id: Int): Boolean
}