package ru.sangel.zaya.presentation.components.main.settings.profile

import android.graphics.Bitmap
import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.entities.States

interface ProfileComponent {
    val model: Value<Model>

    fun changeAvatar()

    fun editField(
        field: SettingType,
        value: String,
    )

    fun getUserAgreegment()

    fun getDataAgreegment()

    fun getPrivacyPolicy()

    data class Model(
        val avatar: Bitmap?,
        val settings: List<ProfileSettingEntity>,
        val state: States,
    )

    data class ProfileSettingEntity(
        val settingType: SettingType,
        val titie: String,
        val placeholder: String,
        val value: String,
        val onValueChanges: (String) -> Unit,
    )

    enum class SettingType {
        LastName,
        Name,
        MiddleName,
        PhoneNumber,
        Email,
    }
}
