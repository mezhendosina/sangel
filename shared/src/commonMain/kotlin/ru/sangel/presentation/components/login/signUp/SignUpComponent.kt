package ru.sangel.presentation.components.login.signUp

import com.arkivanov.decompose.value.Value
import ru.sangel.presentation.entities.States

interface SignUpComponent {
    val model: Value<Model>

    fun changeName(name: String)

    fun changePhone(phone: String)

    fun changeMail(mail: String)

    fun singUp()

    fun onBack()

    data class Model(
        val name: String,
        val phone: String,
        val email: String,
        val state: States,
    )
}
