package ru.sangel.data.users

import ru.sangel.data.users.entities.UserEntity

interface UsersSource {
    suspend fun getNearUsers(): List<UserEntity>

    suspend fun setLocation(
        longtitude: Double,
        latitude: Double,
    )

    suspend fun setStatus(statusId: Int)

    suspend fun getMine(): UserEntity
}
