package ru.sangel.data.users

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.data.BaseKtorfitSource
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.entities.UserEntity

class KtorfitUsersSource(private val ktorfit: Ktorfit) : BaseKtorfitSource(), UsersSource {
    private val userApi = ktorfit.createUsersApi()

    override suspend fun getNearUsers(): List<UserEntity> {
        return listOf(
            UserEntity.nearUser(59.927500, 30.337167),
            UserEntity.nearUser(59.927103, 30.336529),
        )
    }

    override suspend fun setLocation(
        longtitude: Double,
        latitude: Double,
    ) = wrapKtorfitExceptions {
        userApi.updateCoordinates(LocationEntity(latitude, longtitude))
    }

    override suspend fun getMine(): UserEntity =
        wrapKtorfitExceptions {
            val resp = userApi.getMyUser()
            resp
        }
}
