package ru.sangel.data.auth

import kotlinx.coroutines.flow.first
import ru.sangel.UserNotFoundException
import ru.sangel.data.settings.AppPrefs

class AuthRepositoryImpl(
    private val authSource: AuthSource,
    private val appPrefs: AppPrefs,
) : AuthRepository {
    override suspend fun signIn(email: String, password: String) {
        val token = authSource.signIn(email, password)
        saveLoginData(token, email, password)
    }

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        surname: String,
    ) {
        val userId = authSource.signUp(email, password, name, surname)
        appPrefs.setValue(AppPrefs.USER_ID, userId)
        appPrefs.setValue(AppPrefs.EMAIL, email)
    }

    override suspend fun checkCode(code: String) {
        val id = appPrefs.getValue(AppPrefs.USER_ID, null).first() ?: throw UserNotFoundException()
        val token = authSource.checkCode(id, code)
        appPrefs.setValue(AppPrefs.TOKEN, token)
    }

    private suspend fun saveLoginData(
        token: String,
        email: String,
        password: String,
    ) {
        appPrefs.setValue(AppPrefs.TOKEN, token)
        appPrefs.setValue(AppPrefs.PASSWORD, password)
        appPrefs.setValue(AppPrefs.EMAIL, email)
    }
}
