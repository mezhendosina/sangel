package ru.sangel.presentation.components.main.device

import com.arkivanov.decompose.value.Value
import ru.sangel.presentation.entities.DeviceUiEntity

interface DeviceComponent {
    val model: Value<Model>

    fun getUser()

    fun onDeviceClick(address: String)

    fun toAddDevice()

    data class Model(
        val name: String,
        val devices: List<DeviceUiEntity>,
    )
}
