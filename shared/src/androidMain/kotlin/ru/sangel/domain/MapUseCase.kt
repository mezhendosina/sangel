package ru.sangel.app.domain

import android.graphics.BitmapFactory
import com.yandex.mapkit.geometry.Point
import io.ktor.util.decodeBase64Bytes
import kotlinx.coroutines.flow.map
import ru.sangel.app.ui.uiEntities.MapPointUiEntity
import ru.sangel.data.users.UsersRepository
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
                val avatar = if (it.image != null) {
                    val decodeAvatar = Base64.decode(it.image.decodeBase64Bytes(), 0)
                    BitmapFactory.decodeByteArray(decodeAvatar, 0, 0)
                } else null
                MapPointUiEntity(
                    it.id,
                    it.status.id,
                    avatar,
                    it.email,
                    Point(it.latitude.toDouble(), it.longitude.toDouble()),
                    null,
                )
            }
        }
}
