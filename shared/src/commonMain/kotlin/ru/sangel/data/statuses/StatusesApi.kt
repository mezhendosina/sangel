package ru.sangel.data.statuses

import de.jensklingenberg.ktorfit.http.GET
import ru.sangel.API_VERSION

interface StatusesApi {
    @GET("api/$API_VERSION/statuses/")
    suspend fun getStatuses()
}
