package ru.sangel.utils

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

suspend inline fun CoroutineScope.waitUntilBluetoothIsOn(context: Context) {
    val bluetoothAdapter = context.getSystemService(BluetoothManager::class.java)
    while (isActive && bluetoothAdapter.adapter.state == BluetoothAdapter.STATE_OFF) {
        delay(10)
    }
}

suspend fun CoroutineScope.waitUntilPermissionGranted(context: Context) {
    while (isActive && !context.checkBluetoothPermissions()) {
        delay(10)
    }
}

fun Context.checkBluetoothPermissions(): Boolean {
    val bluetoothPermission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
        } else {
            PackageManager.PERMISSION_GRANTED
        }
    return bluetoothPermission == PackageManager.PERMISSION_GRANTED
}
