package ru.sangel.data.users

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import ru.sangel.API_VERSION

interface UsersApi {
    @GET("/api/$API_VERSION/users")
    suspend fun getUsers()

    @GET("/api/v1/users/my")
    suspend fun getMyUser()

    @GET("/api/$API_VERSION/users/{userId}")
    suspend fun getUser(
        @Path("userId") id: Int,
    )

    @POST("/api/v1/users/nearest")
    suspend fun getNearestUser()
}
