package ru.sangel.zaya.data.users

import kotlinx.coroutines.flow.Flow
import ru.sangel.zaya.data.users.entities.UserEntity

interface UsersRepository {
    val nearUsers: Flow<List<ru.sangel.zaya.data.entities.UserEntity>>

    /**
     * Отправляет на сервер местоположение пользователя
     */
    suspend fun setMineLocation(
        latitude: Double,
        longtitude: Double,
    )


    suspend fun getMine(): ru.sangel.zaya.data.entities.UserEntity
}
