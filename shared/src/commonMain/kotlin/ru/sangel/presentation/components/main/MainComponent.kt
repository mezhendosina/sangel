package ru.sangel.presentation.components.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.sangel.presentation.components.main.device.DeviceComponent
import ru.sangel.presentation.components.main.device.connect.ConnectDeviceComponent
import ru.sangel.presentation.components.main.map.MapComponent
import ru.sangel.presentation.components.main.settings.SettingsComponent

interface MainComponent {
    val stack: Value<ChildStack<*, Child>>

    fun onBack()

    fun toDevice()

    fun toSettings()

    fun toMap()

    sealed class Child {
        class Device(val component: DeviceComponent) : Child()

        class AddDevice(val compmonent: ConnectDeviceComponent) : Child()

        class Settings(val component: SettingsComponent) : Child()

        class Map(val component: MapComponent) : Child()
    }
}
