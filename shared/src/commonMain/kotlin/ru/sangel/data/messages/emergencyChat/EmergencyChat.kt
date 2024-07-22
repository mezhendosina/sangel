package ru.sangel.data.messages.emergencyChat

interface EmergencyChat {
    suspend fun sendInitMessage()

    suspend fun sendClaryfingMessage(vararg type: MessageType)

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

expect fun createEmegencyChat(): EmergencyChat
