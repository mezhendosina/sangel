package ru.sangel.presentation.components.main.settings.privacy

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import ru.sangel.ShowLocationTo

class DefaultPrivacyComponent(
    private val componentContext: ComponentContext,
) : PrivacyComponent, ComponentContext by componentContext {
    private val _model =
        MutableValue(
            PrivacyComponent.Model(ShowLocationTo.FAVORITES, ""),
        )

    override val model: Value<PrivacyComponent.Model> = _model

    override fun changeShowLocationTo(value: ShowLocationTo) {
        _model.update {
            PrivacyComponent.Model(
                value,
                it.contactsPreview,
            )
        }
    }
}
