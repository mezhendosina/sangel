package ru.sangel.android

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.sangel.data.device.DeviceRepository
import ru.sangel.data.messages.MessagesRepository

class DeviceService : Service() {
    private val deviceRepository by inject<DeviceRepository>()
    private val messagesRepository by inject<MessagesRepository>()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @OptIn(FlowPreview::class)
    override fun onCreate() {
        super.onCreate()
        startForeground()
        CoroutineScope(Dispatchers.IO).launch {
            deviceRepository.devicesList.debounce(100).collect { advertisement ->
                val findDevice =
                    deviceRepository.connectedDevices.first()
                        .find { it.macAddress == advertisement.identifier }
                if (findDevice != null) {
                    messagesRepository.sendMessageToFavorites()
                }
            }
        }
    }

    private fun startForeground() {
        val notification = createNotification()

        startForeground(
            100,
            notification,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            } else {
                0
            },
        )
    }

    private fun createNotification(): Notification {
        val channel = NotificationChannel("CHANNEL_ID", "Sangel", NotificationManager.IMPORTANCE_LOW)
        channel.description = "test"
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
        return NotificationCompat.Builder(this, "CHANNEL_ID")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }
}
