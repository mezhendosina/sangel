package ru.sangel.zaya.data.messages.source

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.zaya.data.BaseKtorfitSource
import ru.sangel.zaya.data.messages.createNotificationsApi

class KtorfitNotificationsSource(
    private val ktorfit: Ktorfit
) : NotificationsSource, BaseKtorfitSource() {
    private val notificationsApi = ktorfit.createNotificationsApi()

    override suspend fun sendInDanger() = wrapKtorfitExceptions {
        notificationsApi.sendInDanger()
    }

    override suspend fun sendSaving() = wrapKtorfitExceptions {
        notificationsApi.sendSaving()
    }
    override suspend fun sendOk() = wrapKtorfitExceptions {
        notificationsApi.sendOk()
    }
}