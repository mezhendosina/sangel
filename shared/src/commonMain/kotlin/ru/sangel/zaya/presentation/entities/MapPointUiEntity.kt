package ru.sangel.zaya.app.ui.uiEntities

import android.graphics.Bitmap
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject
import ru.sangel.zaya.data.entities.UserStatus

data class MapPointUiEntity(
    val userId: String,
    val type:UserStatus,
    val avatar: Bitmap?,
    val name: String,
    val location: Point,
    val mapObject: PlacemarkMapObject?,
)
