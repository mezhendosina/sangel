package ru.sangel.zaya.presentation.components.main.settings.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import ru.sangel.zaya.presentation.entities.States

class DefaultProfileComponent(
    private val componentContext: ComponentContext,
) : ProfileComponent, ComponentContext by componentContext {
    private val settingsFields =
        listOf(
            ProfileComponent.ProfileSettingEntity(
                ProfileComponent.SettingType.LastName,
                "Фамилия",
                "Иванова",
                "",
            ) { editField(ProfileComponent.SettingType.LastName, it) },
            ProfileComponent.ProfileSettingEntity(
                ProfileComponent.SettingType.LastName,
                "Имя",
                "Алина",
                "",
            ) { editField(ProfileComponent.SettingType.LastName, it) },
            ProfileComponent.ProfileSettingEntity(
                ProfileComponent.SettingType.LastName,
                "Отчество",
                "Вячеславовна",
                "",
            ) { editField(ProfileComponent.SettingType.LastName, it) },
            ProfileComponent.ProfileSettingEntity(
                ProfileComponent.SettingType.LastName,
                "Номер телефона",
                "+7 (952) 812-75-74",
                "",
            ) { editField(ProfileComponent.SettingType.LastName, it) },
            ProfileComponent.ProfileSettingEntity(
                ProfileComponent.SettingType.LastName,
                "Электронная почта",
                "den.levshin.007@mail.ru",
                "",
            ) { editField(ProfileComponent.SettingType.LastName, it) },
        )

    private val _model = MutableValue(ProfileComponent.Model(null, settingsFields, States.Loading))

    override val model: Value<ProfileComponent.Model> = _model

    override fun changeAvatar() {
        TODO("Not yet implemented")
    }

    override fun editField(
        field: ProfileComponent.SettingType,
        value: String,
    ) {
        _model.update {
            it.copy(settings = it.settings.editEntity(field, value))
        }
    }

    private fun List<ProfileComponent.ProfileSettingEntity>.editEntity(
        type: ProfileComponent.SettingType,
        value: String,
    ): List<ProfileComponent.ProfileSettingEntity> {
        val typeIndex = indexOfFirst { it.settingType == type }
        val list = this.toMutableList()
        list[typeIndex] = list[typeIndex].copy(value = value)
        return list
    }

    override fun getUserAgreegment() {
        TODO("Not yet implemented")
    }

    override fun getDataAgreegment() {
        TODO("Not yet implemented")
    }

    override fun getPrivacyPolicy() {
        TODO("Not yet implemented")
    }
}
