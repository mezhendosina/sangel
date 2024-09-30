package ru.sangel.zaya.presentation.components.main.settings

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.components.main.settings.about.AboutAppComponent
import ru.sangel.zaya.presentation.components.main.settings.contacts.ContactsComponent
import ru.sangel.zaya.presentation.components.main.settings.debug.DebugComponent
import ru.sangel.zaya.presentation.components.main.settings.privacy.PrivacyComponent
import ru.sangel.zaya.presentation.components.main.settings.profile.ProfileComponent
import ru.sangel.zaya.presentation.components.main.settings.root.SettingsRootComponent

interface SettingsComponent {
    val stack: Value<ChildStack<*, ru.sangel.zaya.presentation.components.main.settings.SettingsComponent.Child>>

    fun onBack()

    sealed class Child {
        class Root(
            val component: SettingsRootComponent,
        ) : ru.sangel.zaya.presentation.components.main.settings.SettingsComponent.Child()

        class Account(
            val component: ProfileComponent,
        ) : ru.sangel.zaya.presentation.components.main.settings.SettingsComponent.Child()

        class About(
            val component: AboutAppComponent,
        ) : ru.sangel.zaya.presentation.components.main.settings.SettingsComponent.Child()

        class Privacy(
            val component: PrivacyComponent,
        ) : ru.sangel.zaya.presentation.components.main.settings.SettingsComponent.Child()

        class Contacts(
            val component: ContactsComponent,
        ) : ru.sangel.zaya.presentation.components.main.settings.SettingsComponent.Child()

        class Debug(
            val component: DebugComponent,
        ) : ru.sangel.zaya.presentation.components.main.settings.SettingsComponent.Child()
    }
}
