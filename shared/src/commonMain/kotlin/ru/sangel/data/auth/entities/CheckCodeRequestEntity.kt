package ru.sangel.data.auth.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckCodeRequestEntity(
    @SerialName("id")
    val id: Int,
    @SerialName("code")
    val code: String,
)
