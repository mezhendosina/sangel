package ru.sangel.data.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.sangel.app.data.map.MapKitRepository
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.UsersRepository

class MapKitRepositoryImpl(
    private val usersRepository: UsersRepository,
) : MapKitRepository,
    KoinComponent {
    val mapKit = MapKitFactory.getInstance()
    val locationManager = mapKit.createLocationManager()

    private val _zoom = MutableStateFlow(0f)
    override val zoom: StateFlow<Float> = _zoom

    private val _cameraPosition = MutableStateFlow<CameraPosition?>(null)
    override val cameraPosition: StateFlow<CameraPosition?> = _cameraPosition

    override fun plusZoom() {
        CoroutineScope(Dispatchers.IO).launch {
            _zoom.emit(_zoom.value + 1f)
        }
    }

    override fun minusZoom() {
        CoroutineScope(Dispatchers.IO).launch {
            _zoom.emit(_zoom.value - 1f)
        }
    }

    override fun updateCameraPosition(cameraPosition: CameraPosition) {
        _cameraPosition.value = cameraPosition
    }

    override fun setZoom(zoom: Float) {
        CoroutineScope(Dispatchers.IO).launch {
            _zoom.emit(zoom)
        }
    }

    override fun initMap() {
    }

    /**
     * Единоразово отдает местоположение, используя внутренний LocationManager Android
     */
    override fun getLocation(): LocationEntity? {
        val context = get<Context>()
        val locationManager =
            context.getSystemService(android.location.LocationManager::class.java)
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val gpsLocation =
                locationManager.getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER)
            val cellurarLocation =
                locationManager.getLastKnownLocation(android.location.LocationManager.NETWORK_PROVIDER)
            val location =
                if (gpsLocation?.accuracy != null && cellurarLocation?.accuracy != null) {
                    if (gpsLocation.accuracy < cellurarLocation.accuracy) gpsLocation else cellurarLocation
                } else if (gpsLocation?.accuracy != null) {
                    gpsLocation
                } else {
                    cellurarLocation
                }
            if (location == null) {
                return null
            }
            return LocationEntity(location.latitude, location.longitude)
        } else {
            return null
        }
    }

    override suspend fun updateLocation(
        latitude: Double,
        longtitude: Double,
    ) {
        usersRepository.setMineLocation(latitude, longtitude)
    }
}
