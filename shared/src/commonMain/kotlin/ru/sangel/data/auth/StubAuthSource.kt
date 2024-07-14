package ru.sangel.data.auth

class StubAuthSource : AuthSource {

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        surname: String
    ): Int = 1

    override suspend fun signIn(email: String, password: String): String = "auth_token"

    override suspend fun checkCode(id: Int, code: String): String = "auth_token"
}