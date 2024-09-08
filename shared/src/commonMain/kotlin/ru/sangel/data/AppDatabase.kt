package ru.sangel.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sangel.data.contacts.ContactEntity
import ru.sangel.data.contacts.db.ContactsDao
import ru.sangel.data.device.db.DeviceDao
import ru.sangel.data.device.db.DeviceEntity

@Database(entities = [DeviceEntity::class, ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDeviceDao(): DeviceDao

    abstract fun getContactsDao(): ContactsDao
}
