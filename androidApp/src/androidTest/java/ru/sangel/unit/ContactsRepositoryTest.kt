package ru.sangel.unit

import android.Manifest
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.java.KoinJavaComponent
import ru.sangel.data.contacts.ContactsRepository

class ContactsRepositoryTest {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(Manifest.permission.READ_CONTACTS)

    private val contactsRepository: ContactsRepository by KoinJavaComponent.inject(ContactsRepository::class.java)

    @Test
    fun testGetContacts() {
        runBlocking {
            val c = contactsRepository.getContacts()
            assert(c.find { it.name == "Макс" } != null)
        }
    }
}
