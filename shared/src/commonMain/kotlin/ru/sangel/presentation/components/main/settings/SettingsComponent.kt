package ru.sangel.presentation.components.main.settings

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.sangel.presentation.components.main.settings.contacts.ContactsComponent
import ru.sangel.presentation.components.main.settings.debug.DebugComponent
import ru.sangel.presentation.components.main.settings.privacy.PrivacyComponent
import ru.sangel.presentation.components.main.settings.root.SettingsRootComponent

interface SettingsComponent {
    val stack: Value<ChildStack<*, Child>>

    fun onBack()

    sealed class Child {
        class Root(
            val component: SettingsRootComponent,
        ) : Child()

        class Account : Child()

        class About : Child()

        class Privacy(
            val component: PrivacyComponent,
        ) : Child()

        class Contacts(
            val component: ContactsComponent,
        ) : Child()

        class Debug(
            val component: DebugComponent,
        ) : Child()
    }
}
