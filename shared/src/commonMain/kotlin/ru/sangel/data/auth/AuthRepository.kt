package ru.sangel.data.auth

interface AuthRepository {
    suspend fun signIn(email: String)

    suspend fun signUp(
        email: String,
        password: String,
        name: String,
        surname: String,
    )

    suspend fun checkCode(code: String)
}
