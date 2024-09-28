package ru.sangel.zaya.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    @SerialName("email")
    val email: String,
    @SerialName("photo")
    val photo: String?,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("second_name")
    val secondName: String,
    @SerialName("middle_name")
    val middleName: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("devices")
    val devices: List<String>,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("visibility")
    val visibilityType: VisibilityType,
    @SerialName("status")
    val status: UserStatus,
    @SerialName("saving_user")
    val savingUser: Int?,
    @SerialName("in_saving_by")
    val inSavingBy: Int?,
    @SerialName("favorites")
    val favorites: List<String>
) {
    companion object {
        fun stub(): UserEntity =
            UserEntity(
                "",
                "",
                "",
                "",
                "",
                "",
                emptyList(),
                null,
                null,
                VisibilityType.NOBODY,
                UserStatus.OK,
                null,
                null,
                emptyList()
            )
    }
}