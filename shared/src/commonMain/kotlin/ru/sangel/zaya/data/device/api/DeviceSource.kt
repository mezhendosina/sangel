package ru.sangel.zaya.data.device.api

interface DeviceSource {
    suspend fun addDevice(macAddress: String)

    suspend fun deleteDevice(macAddress: String)
}