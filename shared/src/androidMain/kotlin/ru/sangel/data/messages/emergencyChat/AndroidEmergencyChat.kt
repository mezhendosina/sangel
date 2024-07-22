package ru.sangel.data.messages.emergencyChat

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.sangel.EMERGENCY_NUMBMER
import ru.sangel.app.data.map.MapRepository
import ru.sangel.data.messages.MessagesSource
import ru.sangel.data.users.UsersRepository

class AndroidEmergencyChat(
    var emergencyNumber: String = EMERGENCY_NUMBMER,
) : EmergencyChat,
    KoinComponent {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val messageSource: MessagesSource by inject()
    private val mapRepository by inject<MapRepository>()
    private val userRepository by inject<UsersRepository>()

    override suspend fun sendInitMessage() {
        val name = getName()
        val age = getAge()
        val location = getLocation() ?: return
        val firstMassage = "Нападение" + location + "\n" + name.await() + " " + age.await() + " "
        withContext(Dispatchers.Main) {
            messageSource.sendSms(emergencyNumber, firstMassage)
        }
    }

    override suspend fun sendClaryfingMessage(vararg type: EmergencyChat.Companion.MessageType) {
        val message =
            type.mapNotNull {
                when (it) {
                    EmergencyChat.Companion.MessageType.Age -> {
                        userRepository.getMine().email
                    }

                    EmergencyChat.Companion.MessageType.MiddleName -> {
                        userRepository.getMine().latitude
                    }

                    EmergencyChat.Companion.MessageType.PhoneNumber -> {
                        userRepository.getMine().id
                    }

                    else -> null
                }
            }
        if (message.size > 0) {
            messageSource.sendSms(
                emergencyNumber,
                message.joinToString(limit = message.size - 1),
            )
        }
    }

    private suspend fun getLocation(): String? = mapRepository.getLinkLocation()

    private fun getName(): Deferred<String> = coroutineScope.async { "TODO name" }

    private fun getAge(): Deferred<String> = coroutineScope.async { "" }
}

actual fun createEmegencyChat(): EmergencyChat = AndroidEmergencyChat()
