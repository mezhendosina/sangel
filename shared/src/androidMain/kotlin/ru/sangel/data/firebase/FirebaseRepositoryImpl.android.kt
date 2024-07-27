package ru.sangel.data.firebase

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.sangel.data.firebase.FirebaseRepository.Companion.EMERGENCY_NUMBER
import ru.sangel.data.firebase.FirebaseRepository.Companion.INCOMING_EMERGENCY_NUMBER

actual class FirebaseRepositoryImpl :
    FirebaseRepository,
    KoinComponent {
    override fun init() {
        Firebase.initialize(get() as Application)

        val settings =
            remoteConfigSettings {}
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(settings)
        remoteConfig.setDefaultsAsync(FirebaseRepository.defaults)
        remoteConfig.fetch()
    }

    override suspend fun getEmergencyNumber(): String {
        Firebase.remoteConfig.activate()

        return Firebase.remoteConfig.getString(EMERGENCY_NUMBER)
    }

    override suspend fun getIncomingEmergencyNumber(): String {
        Firebase.remoteConfig.activate()

        return Firebase.remoteConfig.getString(INCOMING_EMERGENCY_NUMBER)
    }
}
