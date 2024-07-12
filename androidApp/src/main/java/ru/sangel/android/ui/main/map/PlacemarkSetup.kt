package ru.sangel.android.ui.main.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.TextStyle
import com.yandex.runtime.image.ImageProvider
import ru.sangel.android.R
import ru.sangel.data.users.entities.Status

fun PlacemarkMapObject.setupNearUser(
    context: Context,
    location: Point,
    userImage: Bitmap?,
    name: String
): PlacemarkMapObject {
    geometry = location
    if (userImage == null) setIcon(ImageProvider.fromResource(context, R.drawable.ic_map_pin))
    else setIcon(ImageProvider.fromBitmap(userImage))
    setText(name, TextStyle().apply {
        placement = TextStyle.Placement.BOTTOM
    })
    return this
}