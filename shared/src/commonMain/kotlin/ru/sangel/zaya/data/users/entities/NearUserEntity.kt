package ru.sangel.zaya.data.users.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sangel.zaya.data.entities.UserStatus

@Serializable
data class NearUserEntity(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("photo")
    val photo: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("status")
    val status: UserStatus
)
