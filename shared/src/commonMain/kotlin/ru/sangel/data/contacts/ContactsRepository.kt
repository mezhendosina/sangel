package ru.sangel.data.contacts

import ru.sangel.presentation.components.main.settings.contacts.ContactEntity

interface ContactsRepository {
    suspend fun getContacts(): List<ContactEntity>

    suspend fun getFavorites(): List<ContactEntity>
}
