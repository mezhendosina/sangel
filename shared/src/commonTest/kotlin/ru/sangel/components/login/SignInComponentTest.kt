package ru.sangel.components.login

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.junit.Test
import org.koin.core.component.get
import org.koin.test.KoinTest
import ru.sangel.presentation.components.login.signIn.DefaultSignInComponent
import kotlin.test.assertNotEquals

class SignInComponentTest : KoinTest {
    private val defaultLoginComponent =
        DefaultSignInComponent(get(), get(), DefaultComponentContext(LifecycleRegistry()), {}) {}

    @Test
    fun testEmailChange() {
        val curEmail = defaultLoginComponent.model.value.email
        defaultLoginComponent.onEmailChange("testEmail")
        assertNotEquals(curEmail, defaultLoginComponent.model.value.email)
    }
}
