package ru.sangel.zaya.presentation.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun Context.showLogToast(message: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
