package ru.sangel.zaya.data.auth

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import ru.sangel.zaya.API_VERSION
import ru.sangel.zaya.data.auth.entities.SignInRequestEntity
import ru.sangel.zaya.data.auth.entities.SignInResponseEntity
import ru.sangel.zaya.data.auth.entities.SignUpRequestEntity
import ru.sangel.zaya.data.auth.entities.CheckCodeRequestEntity
import ru.sangel.zaya.data.auth.entities.RefreshTokenRequestEntity
import ru.sangel.zaya.data.auth.entities.RefreshTokenResponseEntity

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
