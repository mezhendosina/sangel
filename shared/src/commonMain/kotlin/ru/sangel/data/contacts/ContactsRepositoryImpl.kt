package ru.sangel.data.contacts

import ru.sangel.presentation.components.main.settings.contacts.ContactEntity

expect class ContactsRepositoryImpl : ContactsRepository {
    override suspend fun getContacts(): List<ContactEntity>
}
