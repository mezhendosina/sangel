package ru.sangel.zaya.data.messages.source

class StubNotificationsSource: NotificationsSource {
    override suspend fun sendInDanger() {

    }

    override suspend fun sendSaving() {
    }

    override suspend fun sendOk() {
    }
}