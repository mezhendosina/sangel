package ru.sangel.data.auth.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequestEntity(
    @SerialName("refresh_token")
    val refreshToken: String,
)
