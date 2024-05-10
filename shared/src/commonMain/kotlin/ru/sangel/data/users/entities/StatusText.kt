package ru.sangel.data.users.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusText(
    @SerialName("id")
    val id: Int,
    @SerialName("text")
    val text: String
)