package ru.sangel.data.messages

import android.content.Context
import android.telephony.SmsManager
import org.koin.core.context.GlobalContext.get

class DefaultMessagesSource(
    val context: Context,
) : MessagesSource {
    override suspend fun sendSms(
        phoneNumber: String,
        message: String,
    ) {
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
        TODO("Not yet implemented")
    }
}
