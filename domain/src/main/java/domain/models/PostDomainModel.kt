package domain.models



data class PostDomainModel(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)