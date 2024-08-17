package ru.sangel.data.auth

import kotlinx.coroutines.flow.first
import ru.sangel.TokenNotFoundException
import ru.sangel.UserNotFoundException
import ru.sangel.data.settings.AppPrefs

class AuthRepositoryImpl(
    private val authSource: AuthSource,
    private val appPrefs: AppPrefs,
) : AuthRepository {
    override suspend fun signIn(
        fcmToken: String,
        email: String,
        password: String,
    ) {
        val token = authSource.signIn(fcmToken, email, password)
        saveLoginData(
            token.accessToken,
            token.refreshToken,
        )
    }

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
    ) {
        authSource.signUp(email, password, firstName, secondName, middleName, phone)
        appPrefs.setValue(AppPrefs.EMAIL, email)
    }

    override suspend fun otp(code: String) {
        val email = appPrefs.getValue(AppPrefs.EMAIL, null).first() ?: throw UserNotFoundException()
        authSource.otp(email, code)
    }

    override suspend fun refreshToken(): String {
        val refreshToken =
            appPrefs.getValue(AppPrefs.REFRESH_TOKEN, null).first()
                ?: throw TokenNotFoundException()
        val token = authSource.refreshToken(refreshToken)
        appPrefs.setValue(AppPrefs.ACCESS_TOKEN, token)
        return token
    }

    private suspend fun saveLoginData(
        accessToken: String,
        refreshToken: String,
    ) {
        appPrefs.setValue(AppPrefs.ACCESS_TOKEN, accessToken)
        appPrefs.setValue(AppPrefs.REFRESH_TOKEN, refreshToken)
    }
}
