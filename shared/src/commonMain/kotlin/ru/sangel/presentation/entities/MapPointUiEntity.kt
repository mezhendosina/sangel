package ru.sangel.app.ui.uiEntities

import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject

data class MapPointUiEntity(
    val userId: Int,
    val type: Int,
//    val avatar: Bitmap,
    val name: String,
    val location: Point,
    val mapObject: PlacemarkMapObject?,
)
