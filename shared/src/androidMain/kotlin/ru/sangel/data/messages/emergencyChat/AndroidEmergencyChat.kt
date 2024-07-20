package ru.sangel.data.messages.emergencyChat

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import ru.sangel.app.data.map.MapKitRepository
import ru.sangel.data.messages.MessagesSource

const val EMERGENCY_NUMBMER = "112"

class AndroidEmergencyChat :
    EmergencyChat,
    KoinComponent {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val messageSource: MessagesSource by inject()
    private val mapKitRepository by inject<MapKitRepository>()

    override suspend fun sendInitMessage() {
        val location = getLocation()
        val name = getName()
        val age = getAge()

        messageSource.sendSms(EMERGENCY_NUMBMER, "Нападение")
        messageSource.sendSms(EMERGENCY_NUMBMER, location.await() ?: return)
        messageSource.sendSms(EMERGENCY_NUMBMER, name.await())
        messageSource.sendSms(EMERGENCY_NUMBMER, age.await())
    }

    override suspend fun sendClaryfingMessage(vararg type: EmergencyChat.Companion.MessageType) {
//        val message =
//            type.map {
//                when (it.subtype) {
//                    EmergencyChat.Companion.MessageSubtype.LOCATION -> {
//                        when (it) {
//                        }
//                    }
//                }
//            }
//        messageSource.sendSms("112", message)
    }

    private fun getLocation(): Deferred<String?> =
        coroutineScope.async {
            val location = mapKitRepository.getLocation() ?: return@async null
            return@async "lat: ${location.latitude} long: ${location.longitude}"
        }

    private fun getName(): Deferred<String> = coroutineScope.async { "TODO" }

    private fun getAge(): Deferred<String> = coroutineScope.async { "" }
}

actual fun createEmegencyChat(): EmergencyChat = AndroidEmergencyChat()
