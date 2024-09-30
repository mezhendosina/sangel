package ru.sangel.zaya.data.auth.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckCodeRequestEntity(
    @SerialName("email")
    val email: String,
    @SerialName("code")
    val code: String,
)
