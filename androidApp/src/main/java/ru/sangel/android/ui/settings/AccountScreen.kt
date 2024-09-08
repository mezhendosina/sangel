package ru.sangel.android.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sangel.android.R
import ru.sangel.presentation.components.main.settings.profile.ProfileComponent

@Composable
fun AccountScreen(
    component: ProfileComponent,
    modifier: Modifier = Modifier,
) {
    val model by component.model.subscribeAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 46.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            EmptyAvatar {
            }
        }

        items(model.settings) { settingEntity ->
            ProfileTextField(
                title = settingEntity.titie,
                hint = settingEntity.placeholder,
                value = settingEntity.value,
                keyboardOptions = KeyboardOptions.Default,
                modifier = Modifier.fillMaxWidth(),
                onValueChanges = { component.editField(settingEntity.settingType, it) },
            )
        }

        item {
            LegalButton(
                label = "Пользовательское соглашение",
                onClick = component::getPrivacyPolicy
            )
        }

        item {
            LegalButton(
                label = "Согласие на обработку персональных данных",
                onClick = component::getDataAgreegment
            )
        }

        item {
            LegalButton(
                label = "Правила пользования устройством",
                onClick = component::getUserAgreegment
            )
        }
    }
}

@Composable
fun LegalButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Image(painter = painterResource(id = R.drawable.ic_document), contentDescription = null)
            Spacer(modifier = modifier.size(10.dp))
            Text(text = label, color = MaterialTheme.colorScheme.outline)
        }
    }
}

@Composable
private fun ProfileTextField(
    title: String,
    hint: String,
    value: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    onValueChanges: (String) -> Unit,
) {
    Column {
        Row {
            Text(text = title, style = MaterialTheme.typography.labelLarge)
        }
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanges,
            placeholder = { Text(text = hint) },
            modifier = modifier,
            shape = RoundedCornerShape(32.dp),
            keyboardOptions = keyboardOptions,
            singleLine = true,
        )
    }
}

@Composable
private fun EmptyAvatar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(modifier = modifier.clickable(onClick = onClick)) {
        Image(
            painter = painterResource(id = R.drawable.ic_change_avatar),
            contentDescription = null,
            modifier = Modifier.size(108.dp),
        )
        Text(
            text = stringResource(R.string.change_photo),
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
