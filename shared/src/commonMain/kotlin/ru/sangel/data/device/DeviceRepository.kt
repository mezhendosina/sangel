package ru.sangel.data.device

import com.juul.kable.Advertisement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import ru.sangel.data.device.db.DeviceEntity
import ru.sangel.presentation.entities.DeviceUiEntity

interface DeviceRepository {
    val avaliableDevice: Flow<Advertisement>
    val pairedDevices: Flow<List<DeviceUiEntity>>
    val emergency: SharedFlow<Boolean>

    suspend fun getDeviceFromDb(address: String): DeviceEntity?

    suspend fun connect(address: String)
}
