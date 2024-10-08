package ru.sangel.zaya.presentation.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.java.KoinJavaComponent.inject
import ru.sangel.zaya.ROOT_STACK
import ru.sangel.zaya.data.map.MapRepository
import ru.sangel.zaya.data.auth.AuthRepository
import ru.sangel.zaya.data.device.DeviceRepository
import ru.sangel.zaya.data.firebase.FirebaseRepository
import ru.sangel.zaya.data.settings.AppPrefs
import ru.sangel.zaya.data.users.UsersRepository
import ru.sangel.zaya.presentation.components.login.DefaultLoginComponent
import ru.sangel.zaya.presentation.components.login.LoginComponent
import ru.sangel.zaya.presentation.components.main.DefaultMainComponent
import ru.sangel.zaya.presentation.components.main.MainComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val startScreen: TopConfig,
) : RootComponent,
    ComponentContext by componentContext {
    private val navigation = StackNavigation<TopConfig>()

    private val authRepository: AuthRepository by inject(AuthRepository::class.java)
    private val appPrefs: AppPrefs by inject(AppPrefs::class.java)
    private val usersRepository: UsersRepository by inject(UsersRepository::class.java)
    private val mapRepository: MapRepository by inject(MapRepository::class.java)
    private val deviceRepository: DeviceRepository by inject(DeviceRepository::class.java)
    private val firebaseRepository: FirebaseRepository by inject(FirebaseRepository::class.java)

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
        DefaultLoginComponent(
            authRepository,
            firebaseRepository = firebaseRepository,
            appPrefs = appPrefs,
            componentContext,
        ) {
            navigation.push(TopConfig.Main)
        }

    private fun mainComponent(componentContext: ComponentContext): MainComponent =
        DefaultMainComponent(
            componentContext,
            usersRepository,
            mapRepository,
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
