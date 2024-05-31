package ru.sangel.app.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseEntity(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("surname")
    val surname: String,
)
