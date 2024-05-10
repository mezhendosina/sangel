package ru.sangel.app.domain

import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.map
import ru.sangel.app.ui.uiEntities.MapPointUiEntity
import ru.sangel.data.users.UsersRepository
import kotlin.io.encoding.ExperimentalEncodingApi

class MapUseCase constructor(
    private val usersRepository: UsersRepository,
) {
    @OptIn(ExperimentalEncodingApi::class)
    val mapPoints =
        usersRepository.nearUsers.map { userEntities ->
            val out = mutableListOf<MapPointUiEntity>()
            userEntities.forEach {
                if (it.latitude == null || it.longitude == null) return@forEach
//            val decodeAvatar = Base64.decode(it.image, 0)
//            val avatar = BitmapFactory.decodeByteArray(ByteArray(0), 0, 0)
                out.add(
                    MapPointUiEntity(
                        it.id,
                        it.status.id,
                        it.email,
                        Point(it.latitude.toDouble(), it.longitude.toDouble()),
                        null,
                    ),
                )
            }
            return@map out.toList()
        }
}
