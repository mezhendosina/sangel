package ru.sangel.data.users

import ru.sangel.data.entities.UserEntity
import ru.sangel.data.users.entities.NearUserEntity


interface UsersSource {
    suspend fun getNearUsers(): List<NearUserEntity>

    suspend fun setLocation(
        longitude: Double,
        latitude: Double,
    )


    suspend fun getMine(): UserEntity
}
