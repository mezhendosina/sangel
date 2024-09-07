package ru.sangel.data.map

import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.sangel.app.data.map.MapRepository
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.UsersRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MapRepositoryImpl(
    private val usersRepository: UsersRepository,
) : MapRepository,
    KoinComponent {
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

    override suspend fun getLinkLocation(): String? {
        val locationManager = get<LocationManager>()
        val location = getLocationCoarse(locationManager)

        return location?.let { "https://yandex.ru/maps/?pt=${it.longitude},${it.latitude}&l=map" }
    }

    override suspend fun updateLocation(
        latitude: Double,
        longtitude: Double,
    ) {
        usersRepository.setMineLocation(latitude, longtitude)
    }

    private suspend fun getLocationCoarse(
        locationManager: LocationManager
    ): LocationEntity? = suspendCoroutine { continuation ->
        val locationListener = object : LocationListener {
            override fun onLocationUpdated(p0: Location) {
                val locationEntity = LocationEntity(p0.position.latitude, p0.position.longitude)
                locationManager.unsubscribe(this)
                continuation.resume(locationEntity)
            }

            override fun onLocationStatusUpdated(p0: LocationStatus) {
                if (p0 == LocationStatus.NOT_AVAILABLE) {
                    locationManager.unsubscribe(this)
                    continuation.resume(null)
                }
            }
        }
        val accuracyMeters = 10.0
        val minUpdateTimeMillis = 1_000L
        val minUpdateDistanceMeters = 10.0
        val allowUseInBackground = false
        val filteringMode = FilteringMode.ON
        locationManager.subscribeForLocationUpdates(
            accuracyMeters,
            minUpdateTimeMillis,
            minUpdateDistanceMeters,
            allowUseInBackground,
            filteringMode,
            locationListener
        )
    }

}
