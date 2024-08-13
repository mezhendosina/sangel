package ru.sangel.data.contacts.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteRequestEntity(
    @SerialName("phone")
    val phone: String,
)
