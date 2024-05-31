package ru.sangel.data.device.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeviceEntity(
    @PrimaryKey val macAddress: String,
    val name: String,
    val batteryCharge: Double,
    val lastUpdated: Long,
)
