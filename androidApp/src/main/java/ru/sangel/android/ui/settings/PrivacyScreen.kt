package ru.sangel.android.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sangel.ShowLocationTo
import ru.sangel.android.R
import ru.sangel.presentation.components.main.settings.privacy.PrivacyComponent

@Composable
fun PrivacyScreen(component: PrivacyComponent) {
    val model by component.model.subscribeAsState()
    LazyColumn {
        item {
            Text("Кто может видеть вашу геопозицию?", modifier = Modifier.padding(start = 16.dp))
            PrivacyRadioButton(
                model.selectedShowLocationTo == ShowLocationTo.FAVORITES,
                { component.changeShowLocationTo(ShowLocationTo.FAVORITES) },
                stringResource(R.string.favorites),
                model.contactsPreview,
            )
            PrivacyRadioButton(
                model.selectedShowLocationTo == ShowLocationTo.ALL,
                { component.changeShowLocationTo(ShowLocationTo.ALL) },
                stringResource(R.string.all_around),
                stringResource(R.string.all_around_caption),
            )
        }
    }
}

@Composable
private fun PrivacyRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    title: String,
    caption: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp),
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
        )
        Column {
            Text(title)
            Text(text = caption)
        }
    }
}
