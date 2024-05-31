package ru.sangel.presentation.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.sangel.presentation.components.login.LoginComponent
import ru.sangel.presentation.components.main.MainComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    fun onBack()

    sealed class Child {
        class LoginChild(val component: LoginComponent) : Child()

        class MainChild(val component: MainComponent) : Child()
    }
}
