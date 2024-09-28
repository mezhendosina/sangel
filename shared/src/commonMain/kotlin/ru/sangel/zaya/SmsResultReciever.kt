package ru.sangel.zaya

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class SmsResultReciever : BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        Toast.makeText(context, getResultByCode(), Toast.LENGTH_SHORT).show()
    }

    private fun getResultByCode(): String =
        when (resultCode) {
            Activity.RESULT_OK -> "Отправлено"
            else -> resultCode.toString()
        }
}
