package ru.sangel.presentation.components.main.settings.root

interface SettingsRootComponent {
    val items: List<Pair<String, () -> Unit>>
}
