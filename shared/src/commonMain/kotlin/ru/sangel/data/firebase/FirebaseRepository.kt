package ru.sangel.data.firebase

interface FirebaseRepository {
    fun init()

    suspend fun getEmergencyNumber(): String

    suspend fun getIncomingEmergencyNumber(): String

    companion object {
        const val EMERGENCY_NUMBER = "emergencyNumber"
        const val INCOMING_EMERGENCY_NUMBER = "incomingEmergencyNumber"
        val defaults =
            mapOf(
                EMERGENCY_NUMBER to "+79120398681",
                INCOMING_EMERGENCY_NUMBER to "+",
            )
    }
}
