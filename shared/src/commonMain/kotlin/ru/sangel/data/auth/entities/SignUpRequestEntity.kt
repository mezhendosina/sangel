package ru.sangel.app.data.entities

data class SignUpRequestEntity(
    val email: String,
    val password: String,
    val name: String,
    val surname: String
)