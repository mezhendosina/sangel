package ru.sangel.presentation.components.main.settings.privacy

import com.arkivanov.decompose.value.Value
import ru.sangel.ShowLocationTo

interface PrivacyComponent {
    val model: Value<Model>

    fun changeShowLocationTo(value: ShowLocationTo)

    data class Model(
        val selectedShowLocationTo: ShowLocationTo,
        val contactsPreview: String,
    )
}
