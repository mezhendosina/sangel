package ru.sangel.presentation.utils

import android.content.Context
import android.widget.Toast

fun Context.showLogToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
