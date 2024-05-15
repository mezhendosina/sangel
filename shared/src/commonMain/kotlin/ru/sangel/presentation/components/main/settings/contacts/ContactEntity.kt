package ru.sangel.presentation.components.main.settings.contacts

data class ContactEntity(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val favorite: Boolean,
)
