package ru.sangel.zaya.ui.main.map

import android.content.Context
import android.graphics.Bitmap
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.TextStyle
import com.yandex.runtime.image.ImageProvider
import ru.sangel.zaya.R

fun PlacemarkMapObject.setupNearUser(
    context: Context,
    location: Point,
    userImage: Bitmap?,
    name: String,
): PlacemarkMapObject {
    geometry = location
    if (userImage == null) {
        setIcon(
            ImageProvider.fromResource(
                context,
                R.drawable.ic_map_pin,
            ),
            IconStyle().setScale(0.5f),
        )
    } else {
        setIcon(
            ImageProvider.fromBitmap(userImage),
            IconStyle().setScale(0.5f),
        )
    }
    setText(
        name,
        TextStyle().apply {
            placement = TextStyle.Placement.BOTTOM
        },
    )
    return this
}
