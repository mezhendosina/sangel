package ru.sangel.android.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sangel.android.ui.login.LoginContainer
import ru.sangel.android.ui.main.MainContainer
import ru.sangel.presentation.components.root.RootComponent

@Composable
fun RootScreen(component: RootComponent) {
    Children(stack = component.stack) {
        when (val config = it.instance) {
            is RootComponent.Child.LoginChild -> LoginContainer(config.component)
            is RootComponent.Child.MainChild -> MainContainer(config.component)
        }
    }
}
