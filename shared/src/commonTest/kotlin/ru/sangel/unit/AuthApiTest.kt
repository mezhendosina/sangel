package ru.sangel.unit

import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import ru.sangel.zaya.data.auth.AuthApi

class AuthApiTest : KoinTest {
    val authApi: AuthApi by inject()

    @Test
    fun testSignUp() {
        TODO()
    }

    @Test
    fun testSignIn() {
        TODO()
    }

    @Test
    fun testCheckValidCode() {
        TODO()
    }

    @Test
    fun testCheckInvalidCode() {
        TODO()
    }
}
