package ru.sangel.data.messages

interface MessagesSource {
    suspend fun sendSms(
        phoneNumber: String,
        message: String,
    )

    suspend fun sendNotification()
}
