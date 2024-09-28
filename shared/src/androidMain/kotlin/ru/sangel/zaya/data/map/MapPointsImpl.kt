package ru.sangel.zaya.data.map

import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject
import org.koin.core.component.KoinComponent
import ru.sangel.zaya.data.map.MapPoints

class MapPointsImpl :
    MapPoints,
    KoinComponent {
    private val _mapPoints = hashMapOf<String, PlacemarkMapObject>()

    override fun addPoint(id: String, placemark: PlacemarkMapObject) {
        _mapPoints[id] = placemark
    }

    override fun getPoint(id: String): PlacemarkMapObject? = _mapPoints[id]

    override fun deletePoint(id: String) {
        _mapPoints.remove(id)
    }

    override fun updatePoint(id: String, location: Point) {
        val point = _mapPoints[id] ?: return
        point.geometry = location
    }

    override fun isPointExist(id: String): Boolean = _mapPoints[id] != null
}
