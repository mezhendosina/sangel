package ru.sangel.presentation.components.main.map

import ru.sangel.app.data.map.MapRepository

class DefaultMapComponent(
    private val mapRepository: MapRepository,
) : MapComponent {
    override fun toProfile() {
//        TODO("Not yet implemented")
    }

    override fun toQuestion() {
//        TODO("Not yet implemented")
    }

    override fun cameraToUser() {
    }

    override fun plusZoom() = mapRepository.plusZoom()

    override fun minusZoom() = mapRepository.minusZoom()
}
