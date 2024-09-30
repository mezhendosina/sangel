package ru.sangel.zaya.presentation.components.main.device.connect

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.juul.kable.Advertisement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sangel.zaya.data.device.DeviceRepository
import ru.sangel.zaya.presentation.entities.ConnectDeviceEntity

class DefaultConnectDeviceComponent(
    private val componentContext: ComponentContext,
    private val deviceRepository: DeviceRepository,
    private val onConnected: () -> Unit,
) : ConnectDeviceComponent,
    ComponentContext by componentContext {
    private val _model = MutableValue(ConnectDeviceComponent.Model(listOf()))
    override val model: Value<ConnectDeviceComponent.Model> = _model
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun observeForDevices() {
        coroutineScope.launch {
            deviceRepository.getAvaliableDevices().collect(::addToList)
        }
        coroutineScope.launch {
            deviceRepository.pairedDevices
                .distinctUntilChanged { old, new -> old == new }
                .collect {
                    if (it.isNotEmpty()) {
                        withContext(Dispatchers.Main) {
                            onConnected.invoke()
                        }
                    }
                }
        }
    }

    override fun connect(
        id: String,
        onConnected: () -> Unit,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            deviceRepository.connect(id, onConnected)
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
