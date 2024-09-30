package ru.sangel.zaya.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sangel.zaya.presentation.components.root.RootComponent
import ru.sangel.zaya.ui.login.LoginContainer
import ru.sangel.zaya.ui.main.MainContainer

@Composable
fun RootScreen(component: RootComponent) {
    Children(stack = component.stack) {
        when (val config = it.instance) {
            is RootComponent.Child.LoginChild -> LoginContainer(config.component)
            is RootComponent.Child.MainChild -> MainContainer(config.component)
        }
    }
}
