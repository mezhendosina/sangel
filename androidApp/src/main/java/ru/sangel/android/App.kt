package ru.sangel.android

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger
import ru.sangel.di.daoModule
import ru.sangel.di.ktorfitModule
import ru.sangel.di.mapModule
import ru.sangel.di.repoModule
import ru.sangel.di.settingsModule
import ru.sangel.di.sourceModule
import ru.sangel.di.useCaseModule
import ru.sangel.di.viewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("4cdc7740-5bcc-43cd-ad9a-517bf2143366")

        startKoin {
            androidContext(this@App)
            androidLogger()
            slf4jLogger()
            modules(
                listOf(
                    ktorfitModule,
                    sourceModule,
                    repoModule,
                    settingsModule,
                    mapModule,
                    viewModelModule,
                    useCaseModule,
                    daoModule(),
                ),
            )
        }
//        startForegroundService(Intent(this, DeviceService::class.java))
    }
}
