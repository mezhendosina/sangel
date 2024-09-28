package ru.sangel.zaya.utils

import android.bluetooth.BluetoothManager
import android.content.Context


fun Context.isBluetoothAvailable(): Boolean{
    val bluetoothAdapter = getSystemService(BluetoothManager::class.java)
    return bluetoothAdapter.adapter != null
}