package ru.sangel.data.users

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PATCH
import de.jensklingenberg.ktorfit.http.POST
import ru.sangel.API_VERSION
import ru.sangel.data.entities.UserEntity
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.entities.NearUserEntity

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
