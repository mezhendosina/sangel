package ru.sangel.presentation.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.sangel.presentation.components.login.DefaultLoginComponent
import ru.sangel.presentation.components.login.LoginComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Login,
            handleBackButton = true,
            childFactory = ::child,
        )

    override fun onBack() {
        navigation.pop()
    }

    private fun child(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child =
        when (config) {
            is Config.Login -> RootComponent.Child.LoginChild(loginComponent())
        }

    private fun loginComponent(): LoginComponent = DefaultLoginComponent()

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Login : Config
    }
}
