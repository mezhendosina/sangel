package ru.sangel.android.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.android.ui.components.SettingsButton
import ru.sangel.android.ui.theme.SangelTheme
import ru.sangel.presentation.components.main.settings.root.SettingsRootComponent

@Composable
fun SettingsScreen(component: SettingsRootComponent) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(component.items) {
            SettingsButton(text = it.first, onClick = it.second)
        }
    }
}

@Preview
@Composable
private fun PrevSettingsScreen() {
    SangelTheme {
        SettingsScreen(
            object : SettingsRootComponent {
                override val items: List<Pair<String, () -> Unit>>
                    get() =
                        listOf(
                            "Аккаунт" to {},
                            "Аккаунт" to {},
                            "Аккаунт" to {},
                            "Аккаунт" to {},
                        )
            },
        )
    }
}
