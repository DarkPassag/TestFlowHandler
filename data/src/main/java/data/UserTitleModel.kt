package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserTitleModel(
    @SerialName("title")
    private val titlePost : String
) {
}