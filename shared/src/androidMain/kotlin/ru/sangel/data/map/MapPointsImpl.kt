package ru.sangel.data.map

import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject
import org.koin.core.component.KoinComponent

class MapPointsImpl :
    MapPoints,
    KoinComponent {
    private val _mapPoints = hashMapOf<Int, PlacemarkMapObject>()

    override fun addPoint(
        id: Int,
        placemark: PlacemarkMapObject,
    ) {
        _mapPoints[id] = placemark
    }

    override fun getPoint(id: Int): PlacemarkMapObject? = _mapPoints[id]

    override fun deletePoint(id: Int) {
        _mapPoints.remove(id)
    }

    override fun updatePoint(
        id: Int,
        location: Point,
    ) {
        val point = _mapPoints[id] ?: return
        point.geometry = location
    }

    override fun isPointExist(id: Int): Boolean = _mapPoints[id] != null
}
