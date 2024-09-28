package ru.sangel.zaya.data.messages

import de.jensklingenberg.ktorfit.http.POST
import ru.sangel.zaya.API_VERSION

interface NotificationsApi {
    @POST("/api/$API_VERSION/notifications/send-in-danger")
    suspend fun sendInDanger()

    @POST("/api/$API_VERSION/notifications/send-saving")
    suspend fun sendSaving()

    @POST("/api/$API_VERSION/notifications/send-ok")
    suspend fun sendOk()
}
