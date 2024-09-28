package ru.sangel.zaya.data.users

import ru.sangel.zaya.data.entities.UserEntity


interface UsersSource {
    suspend fun getNearUsers(): List<UserEntity>

    suspend fun setLocation(
        longitude: Double,
        latitude: Double,
    )


    suspend fun getMine(): UserEntity
}
