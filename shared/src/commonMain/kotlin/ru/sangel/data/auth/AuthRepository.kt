package ru.sangel.data.auth

interface AuthRepository {
    suspend fun signIn(
        fcmToken: String,
        email: String,
        password: String,
    )

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
    )

    suspend fun otp(code: String)

    suspend fun refreshToken()
}
