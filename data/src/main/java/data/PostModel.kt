package data

import kotlinx.serialization.Serializable


@Serializable
data class PostModel(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
) {}