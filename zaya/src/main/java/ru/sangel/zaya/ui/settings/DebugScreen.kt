package ru.sangel.zaya.ui.settings

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch
import ru.sangel.zaya.ui.components.SettingsButton
import ru.sangel.common.ui.theme.SangelTheme
import ru.sangel.zaya.presentation.components.main.settings.debug.DebugComponent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DebugScreen(
    debugComponent: DebugComponent,
    modifier: Modifier = Modifier,
) {
    val model by debugComponent.model.subscribeAsState()
    val coroutineScope = rememberCoroutineScope()
    val permissions =
        rememberMultiplePermissionsState(
            permissions =
                listOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.SEND_SMS,
                ),
        )

    LaunchedEffect(key1 = Unit) {
        permissions.launchMultiplePermissionRequest()
        coroutineScope.launch {
            debugComponent.observeNumbers()
        }
    }

    LazyColumn(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        item {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable { debugComponent.changeUpdatingNumbers(!model.updateNumbers) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "112: получать номера из Firebase")
                Switch(
                    checked = model.updateNumbers,
                    onCheckedChange = debugComponent::changeUpdatingNumbers,
                )
            }
        }
        item {
            Text(text = "112: SMS о нападении отправлять на")

            OutlinedTextField(
                value = model.phoneNubmer,
                onValueChange = debugComponent::updateNumber,
                label = { Text("Номер телефона") },
                enabled = !model.updateNumbers,
                modifier = Modifier.fillMaxWidth(),
            )

            SettingsButton(text = "Сохранить", !model.updateNumbers){
                debugComponent.saveNumber()
            }
        }
        item {
            Text(text = "112: принимать уточняющие сообщения от")

            OutlinedTextField(
                value = model.incomingPhoneNumber,
                onValueChange = debugComponent::updateIncomingNumber,
                enabled = !model.updateNumbers,
                label = { Text("Номер телефона") },
                modifier = Modifier.fillMaxWidth(),
            )

            SettingsButton(text = "Сохранить", !model.updateNumbers) {
                debugComponent.saveIncomingNumber()
            }
        }

        item {
            SettingsButton(
                text = "Смс избранным контактам",
                onClick = debugComponent::smsTofavorites,
            )
        }

        item {
            SettingsButton(text = "Смс 112", onClick = debugComponent::smsTo112)
        }

        item {
            SettingsButton(
                text = "Уведомление пользователям рядом",
                onClick = debugComponent::notificationToNear,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewDebugScreen() {
    SangelTheme {
        DebugScreen(DebugComponent.stubComponent())
    }
}
