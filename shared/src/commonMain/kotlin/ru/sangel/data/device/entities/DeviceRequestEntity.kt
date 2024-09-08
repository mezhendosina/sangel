package ru.sangel.data.device.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceRequestEntity(
    @SerialName("mac_address")
    val macAddress: String
)
