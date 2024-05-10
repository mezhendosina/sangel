package ru.sangel.app.data.map

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.sangel.data.map.entities.LocationEntity

interface MapKitRepository {
    val currentPosition: Flow<LocationEntity>
    val zoom: StateFlow<Float>

    fun plusZoom()

    fun minusZoom()

    fun setZoom(zoom: Float)

    fun initMap()

    suspend fun updateLocation(
        latitude: Double,
        longtitude: Double,
    )
}
