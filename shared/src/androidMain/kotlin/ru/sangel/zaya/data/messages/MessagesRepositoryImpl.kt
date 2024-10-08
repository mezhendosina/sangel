package ru.sangel.zaya.data.messages

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
import ru.sangel.zaya.EmergencyNumberNotFoundException
import ru.sangel.zaya.LocationNotFoundException
import ru.sangel.zaya.data.map.MapRepository
import ru.sangel.zaya.common.R
import ru.sangel.zaya.data.contacts.ContactsRepository
import ru.sangel.zaya.data.firebase.FirebaseRepository
import ru.sangel.zaya.data.messages.source.NotificationsSource
import ru.sangel.zaya.data.settings.AppPrefs
import ru.sangel.zaya.data.users.UsersRepository
import ru.sangel.zaya.presentation.utils.showLogToast

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
    private val notificationsSource by inject<NotificationsSource>()

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

    override suspend fun sendMessageToPolice(isDebug: Boolean) {
        val name = getName()
        val age = getAge()
        val location = getLocation() ?: throw LocationNotFoundException()
        val firstMassage = "Нападение " + location + "\n" + name.await() + " " + age.await() + " "
        val number =
            appPrefs.getValue(AppPrefs.EMERGENCY_PHONE_NUMBER).first()
                ?: throw EmergencyNumberNotFoundException()
        if (isDebug) get<Context>().showLogToast("Отправка на номер: $number")
        withContext(Dispatchers.Main) {
            messagesSource.sendSms(
                number,
                firstMassage,
            )
        }
    }

    override suspend fun sendClarifyingMessageToPolice(vararg type: MessagesRepository.Companion.MessageType) {
        val number = appPrefs.getValue(AppPrefs.EMERGENCY_PHONE_NUMBER).first() ?: return

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
                        userRepository.getMine().phone
                    }

                    else -> null
                }
            }
        if (message.isNotEmpty()) {
            messagesSource.sendSms(
                number,
                message.joinToString(limit = message.size - 1),
            )
        }
    }

    override suspend fun sendMessagesToNearUsers() {
        notificationsSource.sendInDanger()
    }

    private suspend fun getLocation(): String? = mapRepository.getLinkLocation()

    private fun getName(): Deferred<String> = coroutineScope.async { "TODO имя" }

    private fun getAge(): Deferred<String> = coroutineScope.async { "TODO возраст" }
}
