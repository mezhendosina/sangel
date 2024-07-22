package ru.sangel.data.map

import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.sangel.app.data.map.MapRepository
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.UsersRepository

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
        val locationManager = get<com.yandex.mapkit.location.LocationManager>()
        var location: LocationEntity? = LocationEntity.initValue()
        locationManager.requestSingleUpdate(
            object : LocationListener {
                override fun onLocationUpdated(p0: Location) {
                    location = LocationEntity(p0.position.latitude, p0.position.longitude)
                }

                override fun onLocationStatusUpdated(p0: LocationStatus) {
                    if (p0 == LocationStatus.NOT_AVAILABLE) {
                        location = null
                    }
                }
            },
        )

        while (location == LocationEntity.initValue()) {
            delay(1)
        }

        return if (location != null) "https://yandex.ru/maps/?pt=${location!!.longitude},${location!!.latitude}&l=map" else null
    }

    override suspend fun updateLocation(
        latitude: Double,
        longtitude: Double,
    ) {
        usersRepository.setMineLocation(latitude, longtitude)
    }
}
