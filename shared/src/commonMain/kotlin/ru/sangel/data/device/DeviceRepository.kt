package ru.sangel.data.device

import com.juul.kable.Advertisement
import kotlinx.coroutines.flow.Flow
import ru.sangel.data.device.db.DeviceEntity
import ru.sangel.presentation.entities.DeviceUiEntity

interface DeviceRepository {
    val devicesList: Flow<Advertisement>
    val connectedDevices: Flow<List<DeviceUiEntity>>

    suspend fun getDeviceFromDb(address: String): DeviceEntity?

    suspend fun connect(address: String)
}
