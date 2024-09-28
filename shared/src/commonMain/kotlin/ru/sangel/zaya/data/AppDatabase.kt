package ru.sangel.zaya.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sangel.zaya.data.contacts.ContactEntity
import ru.sangel.zaya.data.contacts.db.ContactsDao
import ru.sangel.zaya.data.device.db.DeviceDao
import ru.sangel.zaya.data.device.db.DeviceEntity

@Database(entities = [DeviceEntity::class, ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDeviceDao(): DeviceDao

    abstract fun getContactsDao(): ContactsDao
}
