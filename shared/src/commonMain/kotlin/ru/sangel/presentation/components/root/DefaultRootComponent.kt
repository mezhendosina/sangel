package ru.sangel.presentation.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.java.KoinJavaComponent.inject
import ru.sangel.ROOT_STACK
import ru.sangel.app.data.map.MapKitRepository
import ru.sangel.data.auth.AuthRepository
import ru.sangel.data.device.DeviceRepository
import ru.sangel.data.settings.AppPrefs
import ru.sangel.data.users.UsersRepository
import ru.sangel.presentation.components.login.DefaultLoginComponent
import ru.sangel.presentation.components.login.LoginComponent
import ru.sangel.presentation.components.main.DefaultMainComponent
import ru.sangel.presentation.components.main.MainComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val startScreen: TopConfig,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<TopConfig>()

    private val authRepository: AuthRepository by inject(AuthRepository::class.java)
    private val appPrefs: AppPrefs by inject(AppPrefs::class.java)
    private val usersRepository: UsersRepository by inject(UsersRepository::class.java)
    private val mapKitRepository: MapKitRepository by inject(MapKitRepository::class.java)
    private val deviceRepository: DeviceRepository by inject(DeviceRepository::class.java)

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            key = ROOT_STACK,
            serializer = TopConfig.serializer(),
            initialConfiguration = startScreen,
            handleBackButton = false,
            childFactory = ::child,
        )

    override fun onBack() {
        navigation.pop()
    }

    private fun child(
        topConfig: TopConfig,
        componentContext: ComponentContext,
    ): RootComponent.Child =
        when (topConfig) {
            is TopConfig.Login -> RootComponent.Child.LoginChild(loginComponent(componentContext))
            is TopConfig.Main -> RootComponent.Child.MainChild(mainComponent(componentContext))
        }

    private fun loginComponent(componentContext: ComponentContext): LoginComponent =
        DefaultLoginComponent(authRepository, appPrefs = appPrefs, componentContext) {
            navigation.push(TopConfig.Main)
        }

    private fun mainComponent(componentContext: ComponentContext): MainComponent =
        DefaultMainComponent(
            componentContext,
            usersRepository,
            mapKitRepository,
            deviceRepository,
        )

    @Serializable
    sealed interface TopConfig {
        @Serializable
        data object Login : TopConfig

        @Serializable
        data object Main : TopConfig
    }
}
