package ru.sangel.data.map

import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject

interface MapPoints {
    fun addPoint(id: Int, placemark: PlacemarkMapObject)

    fun getPoint(id: Int): PlacemarkMapObject?

    fun deletePoint(id: Int)

    fun updatePoint(id: Int, location: Point)

    fun isPointExist(id: Int): Boolean
}