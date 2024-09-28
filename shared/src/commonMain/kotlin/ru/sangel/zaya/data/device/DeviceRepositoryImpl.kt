package ru.sangel.zaya.data.device

import android.bluetooth.le.ScanSettings
import com.juul.kable.Advertisement
import com.juul.kable.BluetoothDisabledException
import com.juul.kable.Filter
import com.juul.kable.ObsoleteKableApi
import com.juul.kable.Scanner
import com.juul.kable.characteristicOf
import com.juul.kable.logs.Hex
import com.juul.kable.logs.Logging
import com.juul.kable.logs.SystemLogEngine
import com.juul.kable.peripheral
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.sangel.zaya.data.AppDatabase
import ru.sangel.zaya.data.device.api.DeviceSource
import ru.sangel.zaya.data.device.db.DeviceDao
import ru.sangel.zaya.data.device.db.DeviceEntity
import ru.sangel.zaya.presentation.entities.DeviceUiEntity

class DeviceRepositoryImpl(
    private val database: AppDatabase,
    private val deviceSource: DeviceSource
) : DeviceRepository {
    private val deviceDao: DeviceDao by lazy {
        database.getDeviceDao()
    }

    @OptIn(ObsoleteKableApi::class)
    private val scanner by lazy {
        Scanner {
            scanSettings =
                ScanSettings
                    .Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                    .build()
            filters =
                listOf(
                    Filter.Name("MyESP32"),
                )
        }
    }

    override val pairedDevices: Flow<List<DeviceUiEntity>> =
        deviceDao.getAll().map { deviceEntities ->
            deviceEntities.map {
                DeviceUiEntity(
                    it.macAddress,
                    it.name,
                )
            }
        }
    private val _emergency = MutableSharedFlow<Boolean>()
    override val emergency: SharedFlow<Boolean> = _emergency

    override suspend fun setEmergency(value: Boolean) = _emergency.emit(value)

    @OptIn(ObsoleteKableApi::class)
    override suspend fun getAvaliableDevices(): Flow<Advertisement> =
        scanner.advertisements.catch { if (it !is SecurityException && it !is BluetoothDisabledException) throw it }

    override suspend fun getDeviceFromDb(address: String): DeviceEntity? = deviceDao.getDevice(address)

    @OptIn(ObsoleteKableApi::class)
    override suspend fun connect(
        address: String,
        onConnected: () -> Unit,
    ) {
        val findDevice = getAvaliableDevices().first { it.identifier == address }
        CoroutineScope(Dispatchers.IO)
            .peripheral(findDevice) {
                logging {
                    engine = SystemLogEngine
                    level = Logging.Level.Data
                    data = Hex
                }

                onServicesDiscovered {
                    val readResult =
                        read(
                            characteristicOf(
                                "4FAFC201-1FB5-459E-8FCC-C5C9C331914B",
                                "BEB5483E-36E1-4688-B7F5-EA07361B26A8",
                            ),
                        )

                    if (readResult.isNotEmpty()) {
                        deviceDao.addDevice(
                            DeviceEntity(
                                address,
                                address,
                                100.0,
                                getTimeMillis(),
                            ),
                        )
                        onConnected()
                        deviceSource.addDevice(address)
                    }
                }
            }.connect()
    }
}
