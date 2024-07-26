package ru.sangel.android

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.arkivanov.decompose.defaultComponentContext
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.sangel.android.ui.root.RootScreen
import ru.sangel.android.ui.theme.SangelTheme
import ru.sangel.app.domain.MapUseCase
import ru.sangel.presentation.MainViewModel
import ru.sangel.presentation.components.root.DefaultRootComponent

class MainActivity :
    FragmentActivity(),
    LocationListener {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMap()
        CoroutineScope(Dispatchers.Main).launch {
            val startScreen = (get() as Deferred<DefaultRootComponent.TopConfig>).await()

            val rootComponent =
                getKoin().inject<DefaultRootComponent> {
                    parametersOf(
                        defaultComponentContext(),
                        startScreen,
                    )
                }
            enableEdgeToEdge()
            setContent {
                SangelTheme {
                    RootScreen(component = rootComponent.value)
                }
            }
            startForegroundService(Intent(this@MainActivity, DeviceService::class.java))
        }
    }

    private fun initMap() {
        get<MapUseCase>().initMap(this)
        get<LocationManager>().subscribeForLocationUpdates(
            0.0,
            100L,
            2.0.toDouble(),
            true,
            FilteringMode.ON,
            this@MainActivity,
        )
    }

    override fun onLocationUpdated(p0: Location) {
        viewModel.updateLocation(p0.position.latitude, p0.position.longitude)
    }

    override fun onLocationStatusUpdated(p0: LocationStatus) {}
}
