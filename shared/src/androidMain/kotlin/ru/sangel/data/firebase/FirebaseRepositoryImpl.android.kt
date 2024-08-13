package ru.sangel.data.firebase

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.google.firebase.messaging.messaging
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import ru.sangel.data.firebase.FirebaseRepository.Companion.EMERGENCY_NUMBER
import ru.sangel.data.firebase.FirebaseRepository.Companion.INCOMING_EMERGENCY_NUMBER
import ru.sangel.data.settings.AppPrefs

actual class FirebaseRepositoryImpl :
    FirebaseRepository,
    KoinComponent {
    private val appPrefs by inject<AppPrefs>()
    private val confingUpdateListener =
        object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                CoroutineScope(Dispatchers.IO).launch {
                    Firebase.remoteConfig.activate()
                    configUpdate.updatedKeys.forEach {
                        when (it) {
                            FirebaseRepository.EMERGENCY_NUMBER -> setEmergencyNumber()
                            FirebaseRepository.INCOMING_EMERGENCY_NUMBER -> setIncomingEmergencyNumber()
                        }
                    }
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {}
        }

    override fun init() {
        Firebase.initialize(get() as Application)

        val settings =
            remoteConfigSettings {}
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(settings)
        remoteConfig.setDefaultsAsync(FirebaseRepository.defaults)
        var config = remoteConfig.addOnConfigUpdateListener(confingUpdateListener)

        CoroutineScope(Dispatchers.IO).launch {
            appPrefs.getValue(AppPrefs.FETCH_EMERGENCY_NUMBERS).collect {
                if (it == false) {
                    config.remove()
                } else {
                    config = remoteConfig.addOnConfigUpdateListener(confingUpdateListener)
                }
            }
        }
    }

    override suspend fun getEmergencyNumber(): String {
        Firebase.remoteConfig.activate()

        return Firebase.remoteConfig.getString(EMERGENCY_NUMBER)
    }

    override suspend fun getIncomingEmergencyNumber(): String {
        Firebase.remoteConfig.activate()

        return Firebase.remoteConfig.getString(INCOMING_EMERGENCY_NUMBER)
    }

    override fun getMessagingToken(): String = Firebase.messaging.token.result

    private suspend fun setEmergencyNumber() {
        val value = Firebase.remoteConfig.getString(EMERGENCY_NUMBER)
        appPrefs.setValue(AppPrefs.EMERGENCY_PHONE_NUMBER, value)
    }

    private suspend fun setIncomingEmergencyNumber() {
        val value = Firebase.remoteConfig.getString(EMERGENCY_NUMBER)
        appPrefs.setValue(AppPrefs.EMERGENCY_PHONE_NUMBER, value)
    }
}
