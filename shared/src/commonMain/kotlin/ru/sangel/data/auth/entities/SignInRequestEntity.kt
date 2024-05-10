package ru.sangel.app.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestEntity(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
)
