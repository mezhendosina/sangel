package ru.sangel.zaya

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.juul.kable.AndroidAdvertisement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.sangel.zaya.data.device.DeviceRepository
import ru.sangel.zaya.data.messages.MessagesRepository
import ru.sangel.zaya.presentation.entities.DeviceUiEntity
import ru.sangel.zaya.utils.isBluetoothAvailable
import ru.sangel.zaya.utils.waitUntilBluetoothIsOn
import ru.sangel.zaya.utils.waitUntilBluetoothPermissionGranted

class DeviceService : Service() {
    private val deviceRepository by inject<DeviceRepository>()
    private val messagesRepository by inject<MessagesRepository>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForeground()
        observeAvailableDevices()
        observeEmergency()
    }

    private fun startForeground() {
        val notification = createNotification()

        startForeground(
            100,
            notification,
        )
    }

    private fun createNotification(): Notification {
        val channel =
            NotificationChannel("CHANNEL_ID", "Sangel", NotificationManager.IMPORTANCE_LOW)
        channel.description = "test"
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
        return NotificationCompat
            .Builder(this, "CHANNEL_ID")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    private fun observeEmergency() {
        coroutineScope.launch {
            deviceRepository.emergency.collect {
                if (it) {
                    messagesRepository.sendMessageToFavorites()
                }
            }
        }
    }

    private fun observeAvailableDevices() {
        if (!isBluetoothAvailable()) return

        coroutineScope.launch {
            waitUntilBluetoothPermissionGranted(this@DeviceService)
            waitUntilBluetoothIsOn(this@DeviceService)
            deviceRepository.getAvaliableDevices().collect {
                if (!isActive) return@collect
                val androidAdvertisement = it as AndroidAdvertisement
                deviceRepository.setEmergency(
                    androidAdvertisement.address in
                        deviceRepository.pairedDevices
                            .first()
                            .map(DeviceUiEntity::macAddress),
                )
            }
        }
    }
}
