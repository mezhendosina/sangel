package ru.sangel.data.users

import kotlinx.coroutines.flow.Flow
import ru.sangel.data.users.entities.UserEntity

interface UsersRepository {
    val nearUsers: Flow<List<UserEntity>>

    /**
     * Отправляет на сервер местоположение пользователя
     */
    suspend fun setMineLocation(
        latitude: Double,
        longtitude: Double,
    )


    suspend fun getMine(): UserEntity
}
