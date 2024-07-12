package ru.sangel.presentation.components.main.settings.privacy

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sangel.ShowLocationTo
import ru.sangel.data.contacts.ContactsRepository
import ru.sangel.data.settings.AppPrefs

class DefaultPrivacyComponent(
    private val componentContext: ComponentContext,
    private val contactsRepository: ContactsRepository,
    private val appPrefs: AppPrefs,
) : PrivacyComponent, ComponentContext by componentContext {
    private val _model =
        MutableValue(
            PrivacyComponent.Model(ShowLocationTo.FAVORITES, ""),
        )

    override val model: Value<PrivacyComponent.Model> = _model

    init {
        getContactsPreview()
    }

    override fun changeShowLocationTo(value: ShowLocationTo) {
        _model.update {
            PrivacyComponent.Model(
                value,
                it.contactsPreview,
            )
        }
        CoroutineScope(Dispatchers.IO).launch {
            appPrefs.setValue(AppPrefs.SHOW_LOCATION_TO, value.name)
        }
    }

    private fun getContactsPreview() {
        CoroutineScope(Dispatchers.IO).launch {
            val favs = contactsRepository.favorites.first()
            var outStr = ""

            favs.forEachIndexed { i, item ->
                if (i > 2) {
                    return@forEachIndexed
                }
                outStr += "${item.name} "
            }
            if (favs.size >= 3) {
                outStr += "и еще ${favs.size - 3}"
            }
            val showLocationTo =
                appPrefs.getValue(AppPrefs.SHOW_LOCATION_TO, "FAVORITES").first() ?: "FAVORITES"

            withContext(Dispatchers.Main) {
                _model.update {
                    PrivacyComponent.Model(ShowLocationTo.valueOf(showLocationTo), outStr)
                }
            }
        }
    }
}
