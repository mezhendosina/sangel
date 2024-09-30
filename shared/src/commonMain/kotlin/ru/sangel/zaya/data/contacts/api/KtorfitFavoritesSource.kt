package ru.sangel.zaya.data.contacts.api

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.zaya.data.BaseKtorfitSource
import ru.sangel.zaya.data.contacts.entities.FavoriteRequestEntity

class KtorfitFavoritesSource(
    private val ktorfit: Ktorfit,
) : BaseKtorfitSource(),
    FavoritesSource {
    private val favoritesApi by lazy { ktorfit.createFavoritesApi() }

    override suspend fun addFavorite(phone: String) =
        wrapKtorfitExceptions {
            favoritesApi.addFavorite(FavoriteRequestEntity(phone))
        }

    override suspend fun delteFavorite(phone: String) =
        wrapKtorfitExceptions {
            favoritesApi.deleteContact(FavoriteRequestEntity(phone))
        }
}
