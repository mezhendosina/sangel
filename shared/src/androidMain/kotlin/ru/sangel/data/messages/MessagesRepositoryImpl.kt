package ru.sangel.data.messages

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
import ru.sangel.R
import ru.sangel.app.data.map.MapRepository
import ru.sangel.data.contacts.ContactsRepository
import ru.sangel.data.settings.AppPrefs

actual class MessagesRepositoryImpl :
    MessagesRepository,
    KoinComponent {
    private val messagesSource: MessagesSource by inject(MessagesSource::class.java)
    private val contactsRepository: ContactsRepository by inject(ContactsRepository::class.java)
    private val appPrefs: AppPrefs by inject(AppPrefs::class.java)
    private val mapRepository by inject<MapRepository>()

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
    }

    override suspend fun sendMessagesToNearUsers() {
    }
}
