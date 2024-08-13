package ru.sangel.data.auth

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import ru.sangel.API_VERSION
import ru.sangel.app.data.entities.SignInRequestEntity
import ru.sangel.app.data.entities.SignInResponseEntity
import ru.sangel.app.data.entities.SignUpRequestEntity
import ru.sangel.data.auth.entities.CheckCodeRequestEntity
import ru.sangel.data.auth.entities.RefreshTokenRequestEntity
import ru.sangel.data.auth.entities.RefreshTokenResponseEntity

interface AuthApi {
    @POST("api/$API_VERSION/auth/login")
    suspend fun signIn(
        @Body signInRequestEntity: SignInRequestEntity,
    ): SignInResponseEntity

    @POST("api/$API_VERSION/auth/sign-up")
    suspend fun signUp(
        @Body signUpRequestEntity: SignUpRequestEntity,
    )

    @POST("api/$API_VERSION/auth/otp")
    suspend fun otp(
        @Body checkCodeRequestEntity: CheckCodeRequestEntity,
    )

    @POST("api/$API_VERSION/auth/refresh-token")
    suspend fun refreshToken(
        @Body refreshTokenRequestEntity: RefreshTokenRequestEntity,
    ): RefreshTokenResponseEntity
}
