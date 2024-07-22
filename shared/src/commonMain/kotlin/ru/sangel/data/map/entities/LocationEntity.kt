package ru.sangel.data.map.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationEntity(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
) {
    companion object {
        fun initValue(): LocationEntity = LocationEntity(-91.0, -180.0)
    }
}
