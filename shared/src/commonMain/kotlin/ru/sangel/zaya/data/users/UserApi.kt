package ru.sangel.zaya.data.users

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PATCH
import de.jensklingenberg.ktorfit.http.POST
import ru.sangel.zaya.API_VERSION
import ru.sangel.zaya.data.entities.UserEntity
import ru.sangel.zaya.data.map.entities.LocationEntity

interface UserApi {
    @GET("api/$API_VERSION/about-user")
    suspend fun getMyUser(): UserEntity

    @PATCH("api/$API_VERSION/location/update-coodinates")
    suspend fun updateCoordinates(
        @Body locationEntity: LocationEntity,
    )

    @POST("api/$API_VERSION/location/near-users")
    suspend fun getNearestUser(): List<UserEntity>
}
