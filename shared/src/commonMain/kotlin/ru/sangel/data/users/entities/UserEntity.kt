package ru.sangel.data.users.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    @SerialName("access")
    val access: Int,
    @SerialName("email")
    val email: String,
    @SerialName("for_contacts")
    val forContacts: List<ForContact>,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String?,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("my_contacts")
    val myContacts: List<MyContact>,
    @SerialName("notification_from")
    val notificationFrom: List<NotificationFrom>,
    @SerialName("notification_to")
    val notificationTo: List<NotificationTo>,
    @SerialName("status")
    val status: Status,
) {
    companion object {
        fun nearUser(
            latitude: Double?,
            longitude: Double?,
        ): UserEntity =
            UserEntity(
                0,
                "123@am.ru",
                emptyList(),
                0,
                null,
                latitude,
                longitude,
                emptyList(),
                emptyList(),
                emptyList(),
                Status(null, 0, StatusText(0, ""), 0, 0),
            )
    }
}
