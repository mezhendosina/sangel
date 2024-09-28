package ru.sangel.zaya.data.users.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    @SerialName("help_user_id")
    val helpUserId: Int?,
    @SerialName("id")
    val id: Int,
    @SerialName("status_text")
    val statusText: StatusText,
    @SerialName("status_text_id")
    val statusTextId: Int,
    @SerialName("user_id")
    val userId: Int,
)
