package ru.sangel.presentation.components.main.device

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import ru.sangel.data.device.DeviceRepository
import ru.sangel.data.users.UsersRepository

class DefaultDeviceComponent(
    private val componentContext: ComponentContext,
    private val deviceRepository: DeviceRepository,
    private val toAddDevice: () -> Unit,
) : DeviceComponent, ComponentContext by componentContext {
    private val _model = MutableValue(DeviceComponent.Model("", emptyList()))

    private val usersRepository: UsersRepository by inject(UsersRepository::class.java)

    override val model: Value<DeviceComponent.Model> = _model

    init {
        CoroutineScope(Dispatchers.IO).launch {
            deviceRepository.connectedDevices.collect { deviceUiEntities ->
                withContext(Dispatchers.Main) {
                    _model.update { oldModel ->
                        DeviceComponent.Model(
                            oldModel.name,
                            deviceUiEntities,
                        )
                    }
                }
            }
        }
    }

    override fun getUser() {
        CoroutineScope(Dispatchers.IO).launch {
//            val user = usersRepository.getMine()
            withContext(Dispatchers.Main) {
//                _model.update {
//                    DeviceComponent.Model(user.email, it.devices)
//                }
            }
        }
    }

    override fun onDeviceClick(address: String) {
    }

    override fun toAddDevice() = toAddDevice.invoke()
}
