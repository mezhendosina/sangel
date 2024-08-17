package ru.sangel.app.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestEntity(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("first_name")
    val name: String,
    @SerialName("second_name")
    val surname: String,
    @SerialName("middle_name")
    val middleName: String,
    @SerialName("phone")
    val phoneNumber: String,
)
