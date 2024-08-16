package ru.sangel.data.messages


interface NotificationsSource {

    suspend fun sendInDanger()

    suspend fun sendSaving()

    suspend fun sendOk()
}
