package ru.sangel.zaya.presentation.components.main.settings.contacts

import com.arkivanov.decompose.value.Value

interface ContactsComponent {
    val model: Value<Model>

    fun initContacts()

    fun editQuery(query: String)

    fun addContact(contact: ContactUiEntity)

    fun deleteContact(contact: ContactUiEntity)

    fun sendContactsList()

    data class Model(
        val query: String,
        val favContacts: List<ContactUiEntity>,
        val contacts: List<ContactUiEntity>,
    )
}
