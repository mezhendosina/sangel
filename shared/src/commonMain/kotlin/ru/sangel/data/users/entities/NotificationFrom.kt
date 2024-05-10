package ru.sangel.data.users.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationFrom(
    @SerialName("id")
    val id: Int,
    @SerialName("is_read")
    val isRead: Int,
    @SerialName("text")
    val text: String,
    @SerialName("user_from_id")
    val userFromId: Int,
    @SerialName("user_to_id")
    val userToId: Int
)