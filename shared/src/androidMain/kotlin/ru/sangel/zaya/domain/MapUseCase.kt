package ru.sangel.zaya.domain

import android.content.Context
import android.graphics.BitmapFactory
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import io.ktor.util.decodeBase64Bytes
import kotlinx.coroutines.flow.map
import ru.sangel.zaya.app.ui.uiEntities.MapPointUiEntity
import ru.sangel.zaya.data.users.UsersRepository
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class MapUseCase(
    private val usersRepository: UsersRepository,
) {
    @OptIn(ExperimentalEncodingApi::class)
    val mapPoints =
        usersRepository.nearUsers.map { userEntities ->
            userEntities.mapNotNull {
                if (it.latitude == null || it.longitude == null) return@mapNotNull null
                val avatar =
                    if (it.photo != null) {
                        val decodeAvatar = Base64.decode(it.photo.decodeBase64Bytes(), 0)
                        BitmapFactory.decodeByteArray(decodeAvatar, 0, 0)
                    } else {
                        null
                    }
                MapPointUiEntity(
                    it.email,
                    it.status,
                    avatar,
                    it.email,
                    Point(it.latitude.toDouble(), it.longitude.toDouble()),
                    null,
                )
            }
        }

    fun initMap(context: Context) {
        MapKitFactory.initialize(context)
    }
}
