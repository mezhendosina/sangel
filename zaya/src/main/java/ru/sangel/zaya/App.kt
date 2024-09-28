package ru.sangel.zaya

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import ru.sangel.zaya.data.firebase.FirebaseRepository
import ru.sangel.zaya.di.commonModule
import ru.sangel.zaya.di.platfromModule

class App :
    Application(),
    KoinComponent {
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

        initFirebase()
        MapKitFactory.setApiKey("4cdc7740-5bcc-43cd-ad9a-517bf2143366")
    }

    private fun initFirebase() {
        get<FirebaseRepository>().init()
    }
}
