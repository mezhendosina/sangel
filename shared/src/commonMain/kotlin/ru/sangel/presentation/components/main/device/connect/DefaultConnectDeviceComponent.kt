package ru.sangel.presentation.components.main.device.connect

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.juul.kable.Advertisement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sangel.data.device.DeviceRepository
import ru.sangel.presentation.entities.ConnectDeviceEntity

class DefaultConnectDeviceComponent(
    private val deviceRepository: DeviceRepository,
    private val onConnected: () -> Unit,
) : ConnectDeviceComponent {
    private val _model = MutableValue(ConnectDeviceComponent.Model(listOf()))
    override val model: Value<ConnectDeviceComponent.Model> = _model

    init {
        CoroutineScope(Dispatchers.IO).launch {
            deviceRepository.avaliableDevice.collect(::addToList)
        }
        CoroutineScope(Dispatchers.IO).launch {
            deviceRepository.pairedDevices.distinctUntilChanged { old, new -> old == new }
                .collect {
                    if (it.isNotEmpty()) {
                        withContext(Dispatchers.Main) {
                            onConnected.invoke()
                        }
                    }
                }
        }
    }

    override fun connect(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            deviceRepository.connect(id)
        }
    }

    private suspend fun addToList(advertisement: Advertisement) {
        if (deviceRepository.getDeviceFromDb(advertisement.identifier) != null) return

        if (model.value.list.find { it.id == advertisement.identifier } == null) {
            withContext(Dispatchers.Main) {
                _model.update {
                    ConnectDeviceComponent.Model(
                        it.list.plus(
                            ConnectDeviceEntity(
                                advertisement.identifier,
                                advertisement.name ?: "",
                            ),
                        ),
                    )
                }
            }
        }
    }
}
