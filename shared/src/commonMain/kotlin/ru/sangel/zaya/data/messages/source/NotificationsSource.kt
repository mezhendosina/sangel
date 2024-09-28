package ru.sangel.zaya.data.messages.source


interface NotificationsSource {

    suspend fun sendInDanger()

    suspend fun sendSaving()

    suspend fun sendOk()
}
