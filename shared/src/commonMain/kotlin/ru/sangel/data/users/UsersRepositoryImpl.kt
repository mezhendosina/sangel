package ru.sangel.data.users

import android.content.Context
import android.telephony.SmsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.entities.UserEntity

class UsersRepositoryImpl(
    private val context: Context,
    private val usersSource: UsersSource,
) : UsersRepository {
    override val nearUsers: Flow<List<UserEntity>> =
        flow {
            while (true) {
                val nearby = usersSource.getNearUsers()
                emit(nearby)
                delay(10)
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun setMineLocation(
        latitude: Double,
        longtitude: Double,
    ) {
        usersSource.setLocation(latitude, longtitude)
    }

    override suspend fun sendMessage(
        phoneNumber: String,
        location: LocationEntity,
    ) {
        val smsManager = context.getSystemService(SmsManager::class.java)
        smsManager.sendTextMessage(phoneNumber, null, "https://yandex.ru/maps/?ll=${location.latitude},${location.longitude}", null, null)
    }

    override suspend fun sendStatus(statusCode: Int) {
        usersSource.setStatus(statusCode)
    }

    override suspend fun getMine(): UserEntity = usersSource.getMine()
}
