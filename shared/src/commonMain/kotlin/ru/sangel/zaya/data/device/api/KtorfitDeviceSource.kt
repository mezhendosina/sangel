package ru.sangel.zaya.data.device.api

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.zaya.data.BaseKtorfitSource
import ru.sangel.zaya.data.device.entities.DeviceRequestEntity

class KtorfitDeviceSource(
    private val ktorfit: Ktorfit
) : DeviceSource, BaseKtorfitSource() {
    private val deviceApi = ktorfit.createDeviceApi()

    override suspend fun addDevice(macAddress: String) = wrapKtorfitExceptions {
        deviceApi.addDevice(
            DeviceRequestEntity(macAddress)
        )
    }

    override suspend fun deleteDevice(macAddress: String) = wrapKtorfitExceptions {
        deviceApi.deleteDevice(
            DeviceRequestEntity(macAddress)
        )
    }
}