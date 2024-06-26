package ru.sangel.presentation.components.login.signIn

import com.arkivanov.decompose.value.Value
import ru.sangel.presentation.entities.States

interface SignInComponent {
    val model: Value<Model>

    fun onEmailChange(email: String)

    fun signIn()

    fun toCheckCode()

    data class Model(
        val email: String,
        val state: States,
    )
}
