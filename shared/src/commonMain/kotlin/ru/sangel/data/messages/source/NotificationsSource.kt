package ru.sangel.data.messages.source


interface NotificationsSource {

    suspend fun sendInDanger()

    suspend fun sendSaving()

    suspend fun sendOk()
}
