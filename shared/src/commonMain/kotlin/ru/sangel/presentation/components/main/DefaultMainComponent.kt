package ru.sangel.presentation.components.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.sangel.MAIN_STACK
import ru.sangel.app.data.map.MapRepository
import ru.sangel.data.device.DeviceRepository
import ru.sangel.data.users.UsersRepository
import ru.sangel.presentation.components.main.device.DefaultDeviceComponent
import ru.sangel.presentation.components.main.device.connect.DefaultConnectDeviceComponent
import ru.sangel.presentation.components.main.map.DefaultMapComponent
import ru.sangel.presentation.components.main.settings.DefaultSettingsComponent

@OptIn(ExperimentalDecomposeApi::class)
class DefaultMainComponent(
    private val componentContext: ComponentContext,
    private val usersRepository: UsersRepository,
    private val mapRepository: MapRepository,
    private val deviceRepository: DeviceRepository,
) : MainComponent,
    ComponentContext by componentContext {
    private val navigation = StackNavigation<MainConfig>()
    override val stack: Value<ChildStack<*, MainComponent.Child>> =
        childStack(
            source = navigation,
            key = MAIN_STACK,
            serializer = MainConfig.serializer(),
            initialConfiguration = MainConfig.Device,
            handleBackButton = true,
            childFactory = ::child,
        )

    override fun onBack() {
    }

    override fun toDevice() {
        navigation.pushToFront(MainConfig.Device)
    }

    override fun toSettings() {
        navigation.pushToFront(MainConfig.Settings)
    }

    override fun toMap() {
        navigation.pushToFront(MainConfig.Map)
    }

    override fun toAssistant() {
        navigation.pushToFront(MainConfig.Assistant)
    }

    private fun child(
        mainConfig: MainConfig,
        componentContext: ComponentContext,
    ): MainComponent.Child =
        when (mainConfig) {
            is MainConfig.Device ->
                MainComponent.Child.Device(deviceComponent(componentContext))

            is MainConfig.Settings ->
                MainComponent.Child.Settings(
                    defaultSettingsComponent(
                        componentContext,
                    ),
                )

            is MainConfig.Map -> MainComponent.Child.Map(mapComponent())
            is MainConfig.Assistant -> MainComponent.Child.Assistant()
            is MainConfig.AddDevice ->
                MainComponent.Child.AddDevice(
                    connectDeviceComponent(),
                )
        }

    private fun connectDeviceComponent() =
        DefaultConnectDeviceComponent(componentContext, deviceRepository) {
            navigation.pushToFront(MainConfig.Device)
        }

    private fun mapComponent() = DefaultMapComponent(mapRepository = mapRepository)

    private fun deviceComponent(componentContext: ComponentContext) =
        DefaultDeviceComponent(
            componentContext,
            deviceRepository,
        ) {
            navigation.pushToFront(MainConfig.AddDevice)
        }

    private fun defaultSettingsComponent(componentContext: ComponentContext) = DefaultSettingsComponent(componentContext)

    @Serializable
    private sealed interface MainConfig {
        @Serializable
        data object Device : MainConfig

        @Serializable
        data object AddDevice : MainConfig

        @Serializable
        data object Settings : MainConfig

        @Serializable
        data object Map : MainConfig

        @Serializable
        data object Assistant : MainConfig
    }
}
