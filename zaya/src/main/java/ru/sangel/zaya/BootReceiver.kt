package ru.sangel.zaya

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        context.startService(Intent(context, DeviceService::class.java))
    }
}
