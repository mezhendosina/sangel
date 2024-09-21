package ru.sangel.data.firebase

import com.google.android.gms.tasks.Task

interface FirebaseRepository {
    fun init()

    suspend fun getEmergencyNumber(): String

    suspend fun getIncomingEmergencyNumber(): String

    fun getMessagingToken(): Task<String>

    companion object {
        const val EMERGENCY_NUMBER = "emergencyNumber"
        const val INCOMING_EMERGENCY_NUMBER = "incomingEmergencyNumber"
        val defaults =
            mapOf(
                EMERGENCY_NUMBER to "",
                INCOMING_EMERGENCY_NUMBER to "",
            )
    }
}
