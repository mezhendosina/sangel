package ru.sangel.data.contacts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavContact(contact: ContactEntity)

    @Delete
    suspend fun deleteFavContact(contact: ContactEntity)

    @Query("SELECT * FROM ContactEntity")
    fun getFavContacts(): Flow<List<ContactEntity>>
}
