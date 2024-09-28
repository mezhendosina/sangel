package ru.sangel.zaya.data.auth.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseEntity(
    @SerialName("access_token")
    val accessToken: String,
)
