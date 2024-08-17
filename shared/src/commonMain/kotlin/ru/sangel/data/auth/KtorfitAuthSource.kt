package ru.sangel.data.auth

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.app.data.entities.SignInRequestEntity
import ru.sangel.app.data.entities.SignInResponseEntity
import ru.sangel.app.data.entities.SignUpRequestEntity
import ru.sangel.data.BaseKtorfitSource
import ru.sangel.data.auth.entities.CheckCodeRequestEntity
import ru.sangel.data.auth.entities.RefreshTokenRequestEntity

class KtorfitAuthSource(
    private val ktorfit: Ktorfit,
) : BaseKtorfitSource(),
    AuthSource {
    private val authApi = ktorfit.createAuthApi()

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
    ) = wrapKtorfitExceptions {
        authApi.signUp(
            SignUpRequestEntity(
                email = email,
                password = password,
                name = firstName,
                surname = secondName,
                middleName = middleName,
                phoneNumber = phone,
            ),
        )
    }

    override suspend fun signIn(
        fcmToken: String,
        email: String,
        password: String,
    ): SignInResponseEntity =
        wrapKtorfitExceptions {
            authApi.signIn(
                SignInRequestEntity(fcmToken, email, password),
            )
        }

    override suspend fun otp(
        email: String,
        code: String,
    ): Unit =
        wrapKtorfitExceptions {
            authApi.otp(CheckCodeRequestEntity(email, code))
        }

    override suspend fun refreshToken(refreshToken: String): String =
        wrapKtorfitExceptions {
            authApi.refreshToken(RefreshTokenRequestEntity(refreshToken)).accessToken
        }
}
