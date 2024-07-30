package ru.sangel.presentation.components.main.settings.about

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DefaultAboutAppComponent : AboutAppComponent {
    override val model: Value<AboutAppComponent.Model> =
        MutableValue(
            AboutAppComponent.Model(
                listOf(),
            ),
        )
}
