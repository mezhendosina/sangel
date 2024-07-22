package ru.sangel.data.messages

import android.content.Context
import android.telephony.SmsManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.GlobalContext.get

class DefaultMessagesSource :
    MessagesSource,
    KoinComponent {
    override suspend fun sendSms(
        phoneNumber: String,
        message: String,
    ) {
        val context: Context = get()
        val smsManager = context.getSystemService(SmsManager::class.java)
        smsManager.sendTextMessage(
            phoneNumber,
            null,
            message,
            null,
            null,
        )
    }

    override suspend fun sendNotification() {
    }
}
