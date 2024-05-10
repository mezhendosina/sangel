package ru.sangel.data.auth

import de.jensklingenberg.ktorfit.Ktorfit
import ru.sangel.app.data.entities.SignInRequestEntity
import ru.sangel.app.data.entities.SignUpRequestEntity
import ru.sangel.data.BaseKtorfitSource
import ru.sangel.data.auth.entities.CheckCodeRequestEntity

class KtorfitAuthSource(private val ktorfit: Ktorfit) : BaseKtorfitSource(), AuthSource {
    private val authApi = ktorfit.createAuthApi()

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        surname: String,
    ): Int =
        wrapKtorfitExceptions {
            authApi.signUp(
                SignUpRequestEntity(
                    email,
                    password,
                    name,
                    surname,
                ),
            ).id
        }

    override suspend fun signIn(email: String): String =
        wrapKtorfitExceptions {
            return@wrapKtorfitExceptions authApi.signIn(
                SignInRequestEntity(email, password = "ya"),
            ).accessToken
        }

    override suspend fun checkCode(
        id: Int,
        code: String,
    ): String =
        wrapKtorfitExceptions {
            val resp = authApi.checkCode(CheckCodeRequestEntity(id, code))
            return@wrapKtorfitExceptions resp.accessToken
        }
}
