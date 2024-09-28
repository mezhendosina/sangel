package ru.sangel.zaya.data.contacts

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sangel.zaya.presentation.components.main.settings.contacts.ContactUiEntity

@Entity
data class ContactEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val phoneNumber: String,
    val favorite: Boolean,
) {
    fun toUiEntity(): ContactUiEntity =
        ContactUiEntity(
            id = id,
            name = name,
            phoneNumber = phoneNumber,
            favorite = favorite,
        )
}
