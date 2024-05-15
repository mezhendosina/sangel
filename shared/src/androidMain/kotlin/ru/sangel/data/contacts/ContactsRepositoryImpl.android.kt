package ru.sangel.data.contacts

import android.content.Context
import android.provider.ContactsContract
import ru.sangel.presentation.components.main.settings.contacts.ContactEntity

actual class ContactsRepositoryImpl(
    private val context: Context,
) : ContactsRepository {
    private val projection =
        arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )

    override suspend fun getContacts(): List<ContactEntity> {
        val cr = context.contentResolver
        val out = mutableListOf<ContactEntity>()
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
            while (cursor.moveToNext()) {
                out.add(
                    ContactEntity(
                        -1,
                        cursor.getString(nameIndex),
                        "",
                        false,
                    ),
                )
            }
            cursor.close()
        }
        return out
    }

    override suspend fun getFavorites(): List<ContactEntity> {
        TODO("Not yet implemented")
    }
}
