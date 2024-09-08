package ru.sangel.data.messages

interface MessagesRepository {
    suspend fun sendMessageToFavorites()

    suspend fun sendMessageToPolice(isDebug: Boolean = false)

    suspend fun sendClarifyingMessageToPolice(vararg type: MessageType)

    suspend fun sendMessagesToNearUsers()

    companion object {
        enum class MessageSubtype {
            LOCATION,
            PERSONAL_INFO,
        }

        sealed class MessageType(
            val subtype: MessageSubtype,
        ) {
            object HomeNumber : MessageType(MessageSubtype.LOCATION)

            object EntranceNumber : MessageType(MessageSubtype.LOCATION)

            object MiddleName : MessageType(MessageSubtype.PERSONAL_INFO)

            object Age : MessageType(MessageSubtype.PERSONAL_INFO)

            object PhoneNumber : MessageType(MessageSubtype.PERSONAL_INFO)
        }
    }
}
