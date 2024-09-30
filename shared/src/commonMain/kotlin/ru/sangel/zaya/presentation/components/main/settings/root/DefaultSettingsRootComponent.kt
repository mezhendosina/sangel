package ru.sangel.zaya.presentation.components.main.settings.root

import com.arkivanov.decompose.ComponentContext

class DefaultSettingsRootComponent(
    private val componentContext: ComponentContext,
    private val toAccount: () -> Unit,
    private val toPrivacy: () -> Unit,
    private val toAbout: () -> Unit,
    private val toContact: () -> Unit,
    private val toDebug: () -> Unit,
) : SettingsRootComponent,
    ComponentContext by componentContext {
    override val items: List<Pair<String, () -> Unit>> =
        mutableListOf(
            "Аккаунт" to toAccount,
            "Контакты" to toContact,
            "Приватность" to toPrivacy,
            "О приложении" to toAbout,
            "Debug" to toDebug,
        )
}
