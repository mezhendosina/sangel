package ru.sangel.zaya.data.device.api

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import ru.sangel.zaya.API_VERSION
import ru.sangel.zaya.data.device.entities.DeviceRequestEntity

interface DeviceApi {

    @POST("/api/$API_VERSION/device/add")
    suspend fun addDevice(
        @Body deviceRequestEntity: DeviceRequestEntity
    )


    @POST("/api/$API_VERSION/device/delete")
    suspend fun deleteDevice(
        @Body deviceRequestEntity: DeviceRequestEntity
    )
}
