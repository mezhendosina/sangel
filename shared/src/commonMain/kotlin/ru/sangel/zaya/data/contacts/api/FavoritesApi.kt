package ru.sangel.zaya.data.contacts.api

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import ru.sangel.zaya.API_VERSION
import ru.sangel.zaya.data.contacts.entities.FavoriteRequestEntity

interface FavoritesApi {
    @POST("/api/$API_VERSION/favorites/add")
    suspend fun addFavorite(
        @Body favoriteRequestEntity: FavoriteRequestEntity,
    )

    @POST("/api/$API_VERSION/favotites/delete")
    suspend fun deleteContact(
        @Body favoriteRequestEntity: FavoriteRequestEntity,
    )
}
