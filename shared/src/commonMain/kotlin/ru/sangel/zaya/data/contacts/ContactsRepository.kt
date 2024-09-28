package ru.sangel.zaya.data.contacts

import kotlinx.coroutines.flow.Flow
import ru.sangel.zaya.presentation.components.main.settings.contacts.ContactUiEntity

interface ContactsRepository {
    val favorites: Flow<List<ContactUiEntity>>

    suspend fun getContacts(): List<ContactUiEntity>

    suspend fun addFavorite(contactUiEntity: ContactUiEntity)

    suspend fun deleteFavorite(contactUiEntity: ContactUiEntity)
}
