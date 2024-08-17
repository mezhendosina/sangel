package ru.sangel.data.users

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import ru.sangel.data.users.entities.UserEntity

class UsersRepositoryImpl(
    private val usersSource: UsersSource,
) : UsersRepository {

    override val nearUsers: Flow<List<UserEntity>> =
        flow {
            while (currentCoroutineContext().isActive) {
                val nearby = usersSource.getNearUsers()
                emit(nearby)
                delay(5)
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun setMineLocation(
        latitude: Double,
        longtitude: Double,
    ) {
        usersSource.setLocation(latitude, longtitude)
    }


    override suspend fun getMine(): UserEntity {
        return usersSource.getMine()
    }
}
