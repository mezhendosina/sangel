package ru.sangel.data.users

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PATCH
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import ru.sangel.API_VERSION
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.entities.UserEntity
import ru.sangel.data.users.entities.UserStatusRequestEntity

interface UsersApi {
    @GET("api/$API_VERSION/users/")
    suspend fun getUsers(): List<UserEntity>

    @GET("api/v1/users/my/")
    suspend fun getMyUser(): UserEntity

    @GET("api/$API_VERSION/users/{userId}")
    suspend fun getUser(
        @Path("userId") id: Int,
    )

    @PATCH("api/$API_VERSION/users/user_status")
    suspend fun setUserStatus(
        @Body userStatusRequestEntity: UserStatusRequestEntity,
    ): String

    @PATCH("api/v1/users/update_coordinates/")
    suspend fun updateCoordinates(
        @Body locationEntity: LocationEntity,
    )

    @POST("api/v1/users/nearest/")
    suspend fun getNearestUser(): List<UserEntity>
}
