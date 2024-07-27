package ru.sangel.data.messages

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
import ru.sangel.R
import ru.sangel.app.data.map.MapRepository
import ru.sangel.data.contacts.ContactsRepository
import ru.sangel.data.firebase.FirebaseRepository
import ru.sangel.data.settings.AppPrefs
import ru.sangel.data.users.UsersRepository

actual class MessagesRepositoryImpl :
    MessagesRepository,
    KoinComponent {
    private val messagesSource by inject<MessagesSource>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val appPrefs: AppPrefs by inject(AppPrefs::class.java)

    private val mapRepository by inject<MapRepository>()
    private val contactsRepository by inject<ContactsRepository>()
    private val userRepository by inject<UsersRepository>()
    private val firebaseRepository by inject<FirebaseRepository>()

    val emergencyNumber =
        coroutineScope.async(start = CoroutineStart.LAZY) { firebaseRepository.getEmergencyNumber() }

    override suspend fun sendMessageToFavorites() {
        val favs = contactsRepository.favorites.first()
        val context = get<Context>()
        val location = mapRepository.getLinkLocation() ?: return
        favs.forEach {
            withContext(Dispatchers.IO) {
                messagesSource.sendSms(
                    phoneNumber = it.phoneNumber,
                    message = context.getString(R.string.sms_message, location),
                )
            }
        }
    }

    override suspend fun sendMessageToPolice() {
        emergencyNumber.start()
        val name = getName()
        val age = getAge()
        val location = getLocation() ?: return
        val firstMassage = "Нападение" + location + "\n" + name.await() + " " + age.await() + " "
        withContext(Dispatchers.Main) {
            messagesSource.sendSms(
                emergencyNumber.await(),
                firstMassage,
            )
        }
    }

    override suspend fun sendClarifyingMessageToPolice(vararg type: MessagesRepository.Companion.MessageType) {
        emergencyNumber.start()
        val message =
            type.mapNotNull {
                when (it) {
                    MessagesRepository.Companion.MessageType.Age -> {
                        userRepository.getMine().email
                    }

                    MessagesRepository.Companion.MessageType.MiddleName -> {
                        userRepository.getMine().latitude
                    }

                    MessagesRepository.Companion.MessageType.PhoneNumber -> {
                        userRepository.getMine().id
                    }

                    else -> null
                }
            }
        if (message.size > 0) {
            messagesSource.sendSms(
                emergencyNumber.await(),
                message.joinToString(limit = message.size - 1),
            )
        }
    }

    override suspend fun sendMessagesToNearUsers() {
    }

    private suspend fun getLocation(): String? = mapRepository.getLinkLocation()

    private fun getName(): Deferred<String> = coroutineScope.async { "TODO имя" }

    private fun getAge(): Deferred<String> = coroutineScope.async { "TODO возраст" }
}
