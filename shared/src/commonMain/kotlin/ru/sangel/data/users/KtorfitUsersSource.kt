package ru.sangel.data.users

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.data.BaseKtorfitSource
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.entities.NearUserEntity
import ru.sangel.data.users.entities.UserEntity

class KtorfitUsersSource(
    private val ktorfit: Ktorfit,
) : BaseKtorfitSource(),
    UsersSource {
    private val userApi = ktorfit.createUsersApi()

    override suspend fun getNearUsers(): List<NearUserEntity> =
        wrapKtorfitExceptions {
            userApi.getNearestUser()
        }

    override suspend fun setLocation(
        longitude: Double,
        latitude: Double,
    ) = wrapKtorfitExceptions {
        userApi.updateCoordinates(LocationEntity(latitude, longitude))
    }
ц
    override suspend fun getMine(): ru.sangel.data.entities.UserEntity =
        wrapKtorfitExceptions {
            userApi.getMyUser()
        }
}
