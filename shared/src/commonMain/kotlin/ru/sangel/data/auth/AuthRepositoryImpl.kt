package ru.sangel.data.auth

import kotlinx.coroutines.flow.first
import ru.sangel.TokenNotFoundException
import ru.sangel.UserNotFoundException
import ru.sangel.data.settings.AppPrefs

class AuthRepositoryImpl(
    private val authSource: AuthSource,
    private val appPrefs: AppPrefs,
) : AuthRepository {
    private var _email: String? = null

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
        _email = email
    }

    override suspend fun otp(code: String) {
        authSource.otp(_email ?: throw UserNotFoundException(), code)
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
