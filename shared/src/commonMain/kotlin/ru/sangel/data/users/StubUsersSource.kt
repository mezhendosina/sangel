package ru.sangel.data.users

import ru.sangel.data.entities.UserEntity
import ru.sangel.data.users.entities.NearUserEntity

class StubUsersSource : UsersSource {
    override suspend fun getNearUsers(): List<NearUserEntity> = emptyList()
    override suspend fun setLocation(longitude: Double, latitude: Double) {
    }

    override suspend fun getMine(): UserEntity = UserEntity.stub()
}