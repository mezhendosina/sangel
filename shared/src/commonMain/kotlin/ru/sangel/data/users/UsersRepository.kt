package ru.sangel.data.users

import kotlinx.coroutines.flow.Flow
import ru.sangel.data.users.entities.UserEntity

interface UsersRepository {
    val nearUsers: Flow<List<UserEntity>>

    suspend fun setMineLocation(
        latitude: Double,
        longtitude: Double,
    )

    suspend fun getMine(): UserEntity
}
