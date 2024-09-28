package ru.sangel.zaya.data.users.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserStatusRequestEntity(
    @SerialName("help_user_id")
    val helpUserId: Int,
    @SerialName("status_text_id")
    val statusTextId: Int
)