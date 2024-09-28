package ru.sangel.zaya.data.contacts

import android.content.Context
import android.provider.ContactsContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.sangel.zaya.data.AppDatabase
import ru.sangel.zaya.data.contacts.ContactsRepository
import ru.sangel.zaya.data.contacts.api.FavoritesSource
import ru.sangel.zaya.data.contacts.db.ContactsDao
import ru.sangel.zaya.presentation.components.main.settings.contacts.ContactUiEntity

actual class ContactsRepositoryImpl(
    private val database: AppDatabase,
    private val favoritesSource: FavoritesSource,
) : ContactsRepository,
    KoinComponent {
    val contactsDao: ContactsDao by lazy {
        database.getContactsDao()
    }

    private val projection =
        arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )
    override val favorites: Flow<List<ContactUiEntity>> =
        contactsDao.getFavContacts().map {
            it.map { contactEntity -> contactEntity.toUiEntity() }
        }

    override suspend fun getContacts(): List<ContactUiEntity> {
        val cr = get<Context>().contentResolver
        val out = mutableListOf<ContactUiEntity>()
        val cursor =
            cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            )
        cursor?.let {
            val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val id = cursor.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID)
            while (cursor.moveToNext()) {
                out.add(
                    ContactUiEntity(
                        cursor.getInt(id),
                        cursor.getString(nameIndex),
                        cursor.getString(phoneIndex),
                        false,
                    ),
                )
            }
            cursor.close()
        }
        return out
    }

    override suspend fun addFavorite(contactUiEntity: ContactUiEntity) {
        contactsDao.addFavContact(contactUiEntity.toDbEntity())
        favoritesSource.addFavorite(contactUiEntity.phoneNumber)
    }

    override suspend fun deleteFavorite(contactUiEntity: ContactUiEntity) {
        contactsDao.deleteFavContact(contactUiEntity.toDbEntity())
        favoritesSource.delteFavorite(contactUiEntity.phoneNumber)
    }
}
