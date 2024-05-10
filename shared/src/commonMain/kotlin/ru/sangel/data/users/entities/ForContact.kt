package ru.sangel.data.users.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForContact(
    @SerialName("contact_user_id")
    val contactUserId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("user_id")
    val userId: Int
)