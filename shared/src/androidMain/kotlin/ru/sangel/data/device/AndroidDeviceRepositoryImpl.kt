package ru.sangel.data.device

import com.juul.kable.AndroidAdvertisement
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.sangel.data.AppDatabase
import ru.sangel.data.device.db.DeviceDao
import ru.sangel.data.device.db.DeviceEntity
import ru.sangel.presentation.entities.DeviceUiEntity

class DeviceRepositoryImpl(
    private val database: AppDatabase,
) : DeviceRepository {
    val deviceDao: DeviceDao by lazy {
        database.getDeviceDao()
    }

    override val devicesList: Flow<AndroidAdvertisement> =
        Scanner {
            filters =
                listOf(
                    Filter.Name("MyESP32"),
                )
        }.advertisements

    override val connectedDevices: Flow<List<DeviceUiEntity>> =
        deviceDao.getAll().map { deviceEntities ->
            deviceEntities.map {
                DeviceUiEntity(
                    it.macAddress,
                    it.name,
                )
            }
        }

    override suspend fun getDeviceFromDb(address: String): DeviceEntity? = deviceDao.getDevice(address)

    @OptIn(ObsoleteKableApi::class)
    override suspend fun connect(address: String) {
        val findDevice = devicesList.first { it.address == address }
        CoroutineScope(Dispatchers.IO).peripheral(findDevice) {
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
                }
            }
        }.connect()
    }
}
