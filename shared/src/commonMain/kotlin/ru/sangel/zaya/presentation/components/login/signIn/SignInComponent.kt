package ru.sangel.zaya.presentation.components.login.signIn

import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.entities.States

interface SignInComponent {
    val model: Value<Model>

    fun onEmailChange(email: String)

    fun onPasswordChange(password: String)

    fun signIn()

    fun toSignUp()

    fun toCheckCode()

    data class Model(
        val email: String,
        val password: String,
        val state: States,
    )
}
