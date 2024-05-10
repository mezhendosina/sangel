package ru.sangel.presentation.components.main.map

import ru.sangel.app.data.map.MapKitRepository

class DefaultMapComponent(
    private val mapKitRepository: MapKitRepository,
) : MapComponent {
    override fun toProfile() {
        TODO("Not yet implemented")
    }

    override fun toQuestion() {
        TODO("Not yet implemented")
    }

    override fun cameraToUser() {
        TODO("Not yet implemented")
    }

    override fun plusZoom() = mapKitRepository.plusZoom()

    override fun minusZoom() = mapKitRepository.minusZoom()
}
