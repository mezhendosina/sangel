package ru.sangel.zaya.data.users

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.zaya.data.BaseKtorfitSource
import ru.sangel.zaya.data.entities.UserEntity
import ru.sangel.zaya.data.map.entities.LocationEntity

class KtorfitUsersSource(
    private val ktorfit: Ktorfit,
) : BaseKtorfitSource(),
    UsersSource {
    private val userApi = ktorfit.createUserApi()

    override suspend fun getNearUsers(): List<UserEntity> =
        wrapKtorfitExceptions {
            userApi.getNearestUser()
        }

    override suspend fun setLocation(
        longitude: Double,
        latitude: Double,
    ) = wrapKtorfitExceptions {
        userApi.updateCoordinates(LocationEntity(latitude, longitude))
    }

    override suspend fun getMine(): UserEntity =
        wrapKtorfitExceptions {
            userApi.getMyUser()
        }
}
