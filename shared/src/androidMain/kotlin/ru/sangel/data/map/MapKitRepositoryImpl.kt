package ru.sangel.data.map

import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.sangel.app.data.map.MapKitRepository
import ru.sangel.data.map.entities.LocationEntity
import ru.sangel.data.users.UsersRepository

class MapKitRepositoryImpl(
    private val usersRepository: UsersRepository,
) : MapKitRepository {

    override val currentPosition =
        flow {
            val mapKit = MapKitFactory.getInstance()
            val locationManager = mapKit.createLocationManager()

            locationManager.subscribeForLocationUpdates(
                0.0,
                0,
                0.0,
                true,
                FilteringMode.ON,
                object : LocationListener {
                    override fun onLocationUpdated(p0: Location) {
                        CoroutineScope(Dispatchers.Main).launch {
                            emit(
                                LocationEntity(p0.position.latitude, p0.position.longitude),
                            )
                        }
                    }

                    override fun onLocationStatusUpdated(p0: LocationStatus) {
                    }
                },
            )
        }

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

    override suspend fun updateLocation(
        latitude: Double,
        longtitude: Double,
    ) {
        usersRepository.setMineLocation(latitude, longtitude)
    }
}
