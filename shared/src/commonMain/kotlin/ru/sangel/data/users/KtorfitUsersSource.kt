package ru.sangel.data.users

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.data.BaseKtorfitSource
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.entities.UserEntity
import ru.sangel.data.users.entities.UserStatusRequestEntity

class KtorfitUsersSource(
    private val ktorfit: Ktorfit,
) : BaseKtorfitSource(),
    UsersSource {
    private val userApi = ktorfit.createUsersApi()

    override suspend fun getNearUsers(): List<UserEntity> =
        wrapKtorfitExceptions {
            userApi.getNearestUser()
        }

    override suspend fun setLocation(
        longtitude: Double,
        latitude: Double,
    ) = wrapKtorfitExceptions {
        userApi.updateCoordinates(LocationEntity(latitude, longtitude))
    }

    override suspend fun setStatus(statusId: Int) =
        wrapKtorfitExceptions {
            userApi.setUserStatus(UserStatusRequestEntity(-1, statusId))
            return@wrapKtorfitExceptions
        }

    override suspend fun getMine(): UserEntity =
        wrapKtorfitExceptions {
            val resp = userApi.getMyUser()
            resp
        }
}
