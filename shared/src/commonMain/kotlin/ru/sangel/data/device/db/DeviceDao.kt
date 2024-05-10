package ru.sangel.data.device.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDevice(deviceEntity: DeviceEntity)

    @Query("SELECT * FROM DeviceEntity WHERE (macAddress=:address)")
    suspend fun getDevice(address: String): DeviceEntity?

    @Query("SELECT * FROM DeviceEntity")
    fun getAll(): Flow<List<DeviceEntity>>
}
