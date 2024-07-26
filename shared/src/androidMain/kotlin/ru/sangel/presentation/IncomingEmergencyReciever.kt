package ru.sangel.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.sangel.CLARIFYING_MESSAGE_START
import ru.sangel.REQUIRING_INFO_REGEX
import ru.sangel.data.firebase.FirebaseRepository
import ru.sangel.data.messages.emergencyChat.EmergencyChat

class IncomingEmergencyReciever :
    BroadcastReceiver(),
    KoinComponent {
    private val clarrifyingMessageRegEx = Regex(CLARIFYING_MESSAGE_START + REQUIRING_INFO_REGEX)

    private val emergencyChat by inject<EmergencyChat>()
    private val firebaseRepository by inject<FirebaseRepository>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val incomingEmergencyNumber =
        coroutineScope.async(start = CoroutineStart.LAZY) { firebaseRepository.getIncomingEmergencyNumber() }

    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        coroutineScope.launch(Dispatchers.Main) {
            val number = incomingEmergencyNumber.await()
            val message =
                Telephony.Sms.Intents
                    .getMessagesFromIntent(intent)
                    .sortedBy { it.timestampMillis }
                    .filter { it.displayOriginatingAddress == number }
            message.forEach { smsMessage ->
                if (clarrifyingMessageRegEx.matches(smsMessage.displayMessageBody)) {
                    val listOfRequiringInfo =
                        smsMessage.displayMessageBody
                            .removePrefix(CLARIFYING_MESSAGE_START)
                            .split(",")
                            .map { word ->
                                word.dropWhile { it == ' ' }.dropLastWhile { it != ' ' }
                            }.mapNotNull { it.toMessateType() }
                    coroutineScope.launch {
                        emergencyChat.sendClaryfingMessage(*listOfRequiringInfo.toTypedArray())
                    }
                }
            }
        }
    }

    private fun String.toMessateType(): EmergencyChat.Companion.MessageType? =
        when (this) {
            "номер телефона" -> EmergencyChat.Companion.MessageType.PhoneNumber
            "отчество" -> EmergencyChat.Companion.MessageType.MiddleName
            "возраст" -> EmergencyChat.Companion.MessageType.Age
            else -> null
        }
}
