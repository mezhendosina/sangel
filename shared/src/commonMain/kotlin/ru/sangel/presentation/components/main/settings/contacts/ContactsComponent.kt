package ru.sangel.presentation.components.main.settings.contacts

import com.arkivanov.decompose.value.Value

interface ContactsComponent {
    val model: Value<Model>

    fun initContacts()

    fun editQuery(query: String)

    fun sendContactsList()

    data class Model(
        val query: String,
        val favContacts: List<ContactEntity>,
        val contacts: List<ContactEntity>,
    )
}
