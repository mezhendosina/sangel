package ru.sangel.presentation.components.main.settings.contacts

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sangel.data.contacts.ContactsRepository

class DefaultContactsComponent(
    private val componentContext: ComponentContext,
    private val contactsRepository: ContactsRepository,
) : ContactsComponent, ComponentContext by componentContext {
    private val _model =
        MutableValue(
            ContactsComponent.Model(
                "",
                emptyList(),
                emptyList(),
            ),
        )

    override val model: Value<ContactsComponent.Model> = _model

    override fun initContacts() {
        CoroutineScope(Dispatchers.IO).launch {
            val allContacts = contactsRepository.getContacts()
            val favContacts = allContacts.filter { it.favorite }
            val contacts = allContacts.filter { !it.favorite }

            withContext(Dispatchers.Main) {
                _model.update {
                    ContactsComponent.Model(
                        it.query,
                        favContacts,
                        contacts,
                    )
                }
            }
        }
    }

    override fun editQuery(query: String) {
        _model.update {
            ContactsComponent.Model(
                query,
                it.favContacts,
                it.contacts,
            )
        }
    }

    override fun sendContactsList() {
        TODO("Not yet implemented")
    }
}
