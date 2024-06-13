package ru.sangel.android

import android.app.Application
import android.content.Intent
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.sangel.di.commonModule
import ru.sangel.di.platfromModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                commonModule(),
                platfromModule,
            )
        }
        MapKitFactory.setApiKey("4cdc7740-5bcc-43cd-ad9a-517bf2143366")
        Firebase.initialize(this)
        startForegroundService(Intent(this, DeviceService::class.java))
    }
}
