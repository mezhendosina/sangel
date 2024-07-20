package ru.sangel.app.data.map

import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.flow.StateFlow
import ru.sangel.data.map.entities.LocationEntity

interface MapKitRepository {
    val zoom: StateFlow<Float>

    val cameraPosition: StateFlow<CameraPosition?>

    fun plusZoom()

    fun minusZoom()

    fun updateCameraPosition(cameraPosition: CameraPosition)

    fun setZoom(zoom: Float)

    fun initMap()

    /**
     * Единоразово отдает местоположение, используя внутренний LocationManager Android
     */
    fun getLocation(): LocationEntity?

    suspend fun updateLocation(
        latitude: Double,
        longtitude: Double,
    )
}
