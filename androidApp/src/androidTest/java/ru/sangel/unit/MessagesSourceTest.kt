package ru.sangel.unit

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.inject
import ru.sangel.data.messages.DefaultMessagesSource
import ru.sangel.data.messages.MessagesSource

@RunWith(AndroidJUnit4::class)
class MessagesSourceTest {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(Manifest.permission.SEND_SMS)

    private val messagesRepo: DefaultMessagesSource by inject(MessagesSource::class.java)

    @Test
    fun testSendSms() {
        runBlocking {
            assertDoesNotThrow {
                messagesRepo.sendSms(
                    "",
                    "test",
                )
            }
        }
    }
}
