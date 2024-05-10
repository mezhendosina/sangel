package ru.sangel.presentation.components.main.device.connect

import com.arkivanov.decompose.value.Value
import ru.sangel.presentation.entities.ConnectDeviceEntity

interface ConnectDeviceComponent {
    val model: Value<Model>

    fun connect(id: String)

    data class Model(
        val list: List<ConnectDeviceEntity>,
    )
}
