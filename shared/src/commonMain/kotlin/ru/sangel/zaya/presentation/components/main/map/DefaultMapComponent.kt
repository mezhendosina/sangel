package ru.sangel.zaya.presentation.components.main.map

import ru.sangel.zaya.data.map.MapRepository

class DefaultMapComponent(
    private val mapRepository: MapRepository,
) : MapComponent {

    override fun cameraToUser() {
    }

    override fun plusZoom() = mapRepository.plusZoom()

    override fun minusZoom() = mapRepository.minusZoom()
}
