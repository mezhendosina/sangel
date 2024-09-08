package ru.sangel.data.map

import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject

interface MapPoints {
    fun addPoint(id: String, placemark: PlacemarkMapObject)

    fun getPoint(id: String): PlacemarkMapObject?

    fun deletePoint(id: String)

    fun updatePoint(id: String, location: Point)

    fun isPointExist(id: String): Boolean
}