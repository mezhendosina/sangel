package ru.sangel.data.auth

import ru.sangel.app.data.entities.SignInResponseEntity

interface AuthSource {
    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
    )

    suspend fun signIn(
        fcmToken: String,
        email: String,
        password: String,
    ): SignInResponseEntity

    suspend fun otp(
        email: String,
        code: String,
    )

    suspend fun refreshToken(refreshToken: String): String
}
