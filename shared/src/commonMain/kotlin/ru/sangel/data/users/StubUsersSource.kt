package ru.sangel.data.users

import ru.sangel.data.users.entities.UserEntity

class StubUsersSource : UsersSource {
    override suspend fun getNearUsers(): List<UserEntity> = listOf(UserEntity.nearUser(59.98450858533972, 30.239196718769822), UserEntity.nearUser(60.01318763165601, 33.49689569339222))
    override suspend fun setLocation(longtitude: Double, latitude: Double) {
    }

    override suspend fun setStatus(statusId: Int) {
    }

    override suspend fun getMine(): UserEntity = UserEntity.stub()
}