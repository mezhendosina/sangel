package ru.sangel.data.contacts

import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path

interface ContactsApi {
    @POST("/api/v1/contacts")
    suspend fun getContacts()

    @DELETE("/api/v1/contacts/{contact_id}")
    suspend fun deleteContact(
        @Path("contact_id") id: Int,
    )
}
