package ru.sangel.data.messages

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import ru.sangel.data.contacts.ContactsRepository

actual class MessagesRepositoryImpl() : MessagesRepository {
    private val messagesSource: MessagesSource by inject(MessagesSource::class.java)
    private val contactsRepository: ContactsRepository by inject(ContactsRepository::class.java)

    override suspend fun sendMessageToFavorites() {
        val favs = contactsRepository.favorites.first()
        favs.forEach {
            withContext(Dispatchers.IO) {
                messagesSource.sendSms(
                    phoneNumber = it.phoneNumber,
                    "",
                )
            }
        }
    }

    override suspend fun sendMessageToPolice() {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessagesToNearUsers() {
        TODO("Not yet implemented")
    }
}
