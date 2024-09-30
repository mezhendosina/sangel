package ru.sangel.zaya.presentation.components.main.settings.privacy

import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.ShowLocationTo

interface PrivacyComponent {
    val model: Value<Model>

    fun changeShowLocationTo(value: ShowLocationTo)

    data class Model(
        val selectedShowLocationTo: ShowLocationTo,
        val contactsPreview: String,
    )
}
