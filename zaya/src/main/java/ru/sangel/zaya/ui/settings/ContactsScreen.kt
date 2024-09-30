package ru.sangel.zaya.ui.settings

import android.content.pm.PackageManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import ru.sangel.zaya.R
import ru.sangel.zaya.ui.components.ContactCard
import ru.sangel.common.ui.theme.SangelTheme
import ru.sangel.zaya.presentation.components.main.settings.contacts.ContactUiEntity
import ru.sangel.zaya.presentation.components.main.settings.contacts.ContactsComponent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactsScreen(component: ContactsComponent) {
    val model by component.model.subscribeAsState()

    val contactPermission =
        rememberPermissionState(permission = android.Manifest.permission.READ_CONTACTS)
    if (!contactPermission.status.isGranted) {
        LaunchedEffect(Unit) {
            contactPermission.launchPermissionRequest()
        }
    }
    if (ContextCompat.checkSelfPermission(
            LocalContext.current,
            android.Manifest.permission.READ_CONTACTS,
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        LaunchedEffect(Unit) {
            component.initContacts()
        }
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 46.dp),
        ) {
            OutlinedTextField(
                value = model.query,
                onValueChange = component::editQuery,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(64.dp),
                placeholder = {
                    Text(stringResource(R.string.find_contact))
                },
            )
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.size(32.dp))
                    AnimatedVisibility(visible = model.favContacts.isNotEmpty()) {
                        ContactsTitle(
                            title = stringResource(R.string.fav_contacts),
                        )
                    }
                }
                items(model.favContacts) {
                    ContactCard(
                        name = it.name,
                        checked = true,
                        onClick = { component.deleteContact(it) },
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(32.dp))
                }
                if (model.contacts.isNotEmpty()) {
                    item {
                        ContactsTitle(
                            title = stringResource(R.string.all_contacts),
                        )
                    }
                    items(model.contacts) {
                        ContactCard(
                            name = it.name,
                            checked = it.favorite,
                            onClick = { component.addContact(it) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactsTitle(title: String) {
    Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.size(8.dp))
}

@Preview
@Composable
private fun PreviewContactsScreen() {
    SangelTheme {
        ContactsScreen(
            object : ContactsComponent {
                override val model: Value<ContactsComponent.Model>
                    get() =
                        MutableValue(
                            ContactsComponent.Model(
                                "",
                                listOf(
                                    ContactUiEntity(0, "123", "", true),
                                    ContactUiEntity(0, "123", "", true),
                                    ContactUiEntity(0, "123", "", true),
                                ),
                                listOf(
                                    ContactUiEntity(0, "123", "", true),
                                    ContactUiEntity(0, "123", "", true),
                                    ContactUiEntity(0, "123", "", true),
                                ),
                            ),
                        )

                override fun initContacts() {
                    TODO("Not yet implemented")
                }

                override fun editQuery(query: String) {
                    TODO("Not yet implemented")
                }

                override fun addContact(contact: ContactUiEntity) {
                    TODO("Not yet implemented")
                }

                override fun deleteContact(contact: ContactUiEntity) {
                    TODO("Not yet implemented")
                }

                override fun sendContactsList() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}
