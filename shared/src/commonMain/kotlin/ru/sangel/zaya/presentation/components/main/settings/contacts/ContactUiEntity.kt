package ru.sangel.zaya.presentation.components.main.settings.contacts

import ru.sangel.zaya.data.contacts.ContactEntity

data class ContactUiEntity(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val favorite: Boolean,
) {
    fun toDbEntity(): ContactEntity = ContactEntity(id, name, phoneNumber, favorite)
}
