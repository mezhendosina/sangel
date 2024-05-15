package ru.sangel.data.messages

interface MessagesRepository {
    suspend fun sendMessageToFavorites()

    suspend fun sendMessageToPolice()

    suspend fun sendMessagesToNearUsers()
}
