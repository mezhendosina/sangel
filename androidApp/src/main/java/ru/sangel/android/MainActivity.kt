package ru.sangel.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import com.arkivanov.decompose.defaultComponentContext
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sangel.android.ui.root.RootScreen
import ru.sangel.android.ui.theme.SangelTheme
import ru.sangel.presentation.MainViewModel
import ru.sangel.presentation.components.root.DefaultRootComponent

class MainActivity : FragmentActivity(), LocationListener {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(this)
        MapKitFactory.getInstance().createLocationManager()
            .subscribeForLocationUpdates(
                0.0,
                100L,
                2.0.toDouble(),
                true,
                FilteringMode.ON,
                this@MainActivity,
            )
        CoroutineScope(Dispatchers.Main).launch {
            val defferedStartScreen = get() as Deferred<DefaultRootComponent.TopConfig>

            val rootComponent =
                DefaultRootComponent(
                    defaultComponentContext(),
                    defferedStartScreen.await(),
                )
            enableEdgeToEdge()
            setContent {
                SangelTheme {
                    RootScreen(component = rootComponent)
                }
            }
            startForegroundService(Intent(this@MainActivity, DeviceService::class.java))
        }
    }

    override fun onLocationUpdated(p0: Location) {
        viewModel.updateLocation(p0.position.latitude, p0.position.longitude)
    }

    override fun onLocationStatusUpdated(p0: LocationStatus) {}
}
