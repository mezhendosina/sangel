package ru.sangel.data.contacts.api

interface FavoritesSource {
    suspend fun addFavorite(phone: String)

    suspend fun delteFavorite(phone: String)
}
