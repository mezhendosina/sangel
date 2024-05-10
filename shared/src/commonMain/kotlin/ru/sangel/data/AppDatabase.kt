package ru.sangel.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sangel.data.device.db.DeviceDao
import ru.sangel.data.device.db.DeviceEntity

@Database(entities = [DeviceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDeviceDao(): DeviceDao
}
