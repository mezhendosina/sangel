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

suspend fun CoroutineScope.waitUntilBluetoothIsOn(context: Context) {
    val bluetoothAdapter = context.getSystemService(BluetoothManager::class.java)
    while (isActive && bluetoothAdapter.adapter.state != BluetoothAdapter.STATE_ON) {
        delay(10)
    }
}

suspend fun CoroutineScope.waitUntilBluetoothPermissionGranted(context: Context) {
    while (isActive && !context.checkBluetoothPermissions()) {
        delay(10)
    }
}

fun Context.checkBluetoothPermissions(): Boolean {
    val bluetoothPermission =
        ContextCompat.checkSelfPermission(
            this,
            if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.S
            ) {
                Manifest.permission.BLUETOOTH_SCAN
            } else {
                Manifest.permission.ACCESS_FINE_LOCATION
            },
        )
    return bluetoothPermission == PackageManager.PERMISSION_GRANTED
}
