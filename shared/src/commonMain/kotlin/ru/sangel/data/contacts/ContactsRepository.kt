package ru.sangel.data.contacts

import kotlinx.coroutines.flow.Flow
import ru.sangel.presentation.components.main.settings.contacts.ContactUiEntity

interface ContactsRepository {
    val favorites: Flow<List<ContactUiEntity>>

    suspend fun getContacts(): List<ContactUiEntity>

    suspend fun addFavContact(contactUiEntity: ContactUiEntity)

    suspend fun deleteFavContact(contactUiEntity: ContactUiEntity)
}
