package ru.sangel.data.auth

interface AuthSource {
    /**
     * Регистрирует юзера
     * @return id юзера
     */
    suspend fun signUp(
        email: String,
        password: String,
        name: String,
        surname: String,
    ): Int

    /**
     * Авторизовывает
     * @return Токен авторизации
     */
    suspend fun signIn(email: String, password: String): String

    /**
     * Проверяет код подверждения
     * @return Токен авторизации
     */
    suspend fun checkCode(
        id: Int,
        code: String,
    ): String
}
