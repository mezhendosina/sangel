package ru.sangel.data.auth

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.Headers
import de.jensklingenberg.ktorfit.http.POST
import ru.sangel.API_VERSION
import ru.sangel.app.data.entities.SignInRequestEntity
import ru.sangel.app.data.entities.SignInResponseEntity
import ru.sangel.app.data.entities.SignUpRequestEntity
import ru.sangel.app.data.entities.SignUpResponseEntity
import ru.sangel.data.auth.entities.CheckCodeRequestEntity

interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("api/$API_VERSION/auth/login/")
    suspend fun signUp(
        @Body signUpRequestEntity: SignUpRequestEntity,
    ): SignUpResponseEntity

    @POST("api/$API_VERSION/auth/")
    suspend fun signIn(
        @Body signInRequestEntity: SignInRequestEntity,
    ): SignInResponseEntity

    @POST("api/$API_VERSION/auth/validate/")
    suspend fun checkCode(
        @Body checkCodeRequestEntity: CheckCodeRequestEntity,
    ): SignInResponseEntity
}
