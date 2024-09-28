package ru.sangel.zaya.data.users

import ru.sangel.zaya.data.entities.UserEntity
import ru.sangel.zaya.data.users.entities.NearUserEntity

class StubUsersSource : UsersSource {
    override suspend fun getNearUsers(): List<UserEntity> = emptyList()
    override suspend fun setLocation(longitude: Double, latitude: Double) {
    }

    override suspend fun getMine(): UserEntity = UserEntity.stub()
}