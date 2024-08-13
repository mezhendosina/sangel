package ru.sangel.data.auth

import ru.sangel.app.data.entities.SignInResponseEntity

class StubAuthSource : AuthSource {
    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
    ) {
    }

    override suspend fun signIn(
        fcmToken: String,
        email: String,
        password: String,
    ): SignInResponseEntity = SignInResponseEntity("", "")

    override suspend fun otp(
        email: String,
        code: String,
    ) {
    }

    override suspend fun refreshToken(refreshToken: String): String = ""
}
