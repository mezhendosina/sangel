package ru.sangel.zaya.presentation.components.login.checkCode

import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.entities.States

interface ConfirmCodeComponent {
    val model: Value<Model>

    fun onCodeChanges(code: String)

    fun toMain()

    data class Model(
        val code: String,
        val timer: Int,
        val state: States,
    )
}
