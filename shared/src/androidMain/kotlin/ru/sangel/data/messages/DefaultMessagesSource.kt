package ru.sangel.data.messages

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import androidx.core.app.PendingIntentCompat
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.sangel.SmsResultReciever
import ru.sangel.presentation.utils.showLogToast

class DefaultMessagesSource :
    MessagesSource,
    KoinComponent {
    override suspend fun sendSms(
        phoneNumber: String,
        message: String,
    ) {
        val context: Context = get()
        val smsManager = context.getSystemService(SmsManager::class.java)
        val pendingIntent = getPendingIntent(context)
        val divideMessage = smsManager.divideMessage(message)
        val pendingIntents = pendingIntent?.dublicateAsArrayList(divideMessage.size)
        smsManager.sendMultipartTextMessage(
            phoneNumber,
            null,
            smsManager.divideMessage(message),
            pendingIntents,
            null,
        )
        context.showLogToast("Отправляем")
    }

    private fun getPendingIntent(context: Context): PendingIntent? {
        val intent = Intent(context, SmsResultReciever::class.java)
        val pendingIntent =
            PendingIntentCompat.getBroadcast(
                context,
                -1,
                intent,
                PendingIntent.FLAG_ONE_SHOT,
                true,
            )
        return pendingIntent
    }

    override suspend fun sendNotification() {
    }

    private fun PendingIntent.dublicateAsArrayList(size: Int): ArrayList<PendingIntent> {
        val outArray: ArrayList<PendingIntent> = arrayListOf()
        repeat(size) {
            outArray.add(this)
        }
        return outArray
    }
}
