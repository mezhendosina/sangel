package ru.sangel.components.login

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import ru.sangel.zaya.data.auth.AuthRepository
import ru.sangel.zaya.presentation.components.login.signUp.DefaultSignUpComponent
import ru.sangel.zaya.presentation.components.login.signUp.SignUpComponent
import ru.sangel.zaya.presentation.entities.States
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultSignUpComponentTest {

    private val testDispatcher = StandardTestDispatcher()

    private val authRepository: AuthRepository = mock()
    private val onBack: () -> Unit = mock()
    private val toCheckCode: () -> Unit = mock()

    private val signUpComponent = DefaultSignUpComponent(
        "",
        authRepository,
        DefaultComponentContext(object : Lifecycle {
            override val state: Lifecycle.State
                get() = Lifecycle.State.INITIALIZED

            override fun subscribe(callbacks: Lifecycle.Callbacks) {
            }

            override fun unsubscribe(callbacks: Lifecycle.Callbacks) {
            }
        }),
        onBack,
        testDispatcher,
        toCheckCode,
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `WHEN changeName THEN check model`() {
        val name = "changeName test"
        signUpComponent.changeName(name)

        assertEquals(name, signUpComponent.model.value.firstName)
    }

    @Test
    fun `WHEN changePhone THEN check model`() {
        val name = "changePhone test"
        signUpComponent.changePhone(name)

        assertEquals(name, signUpComponent.model.value.phoneNumber)
    }

    @Test
    fun `WHEN changeMail THEN check model`() {
        val name = "changeMail test"
        signUpComponent.changeMail(name)

        assertEquals(name, signUpComponent.model.value.email)
    }

    @Test
    fun `WHEN changePassword THEN check model`() {
        val name = "changePassword test"
        signUpComponent.changePassword(name)

        assertEquals(name, signUpComponent.model.value.password)
    }

    @Test
    fun `GIVEN wrong email WHEN singUp THEN check model`() = runTest {
        val email = "test wrong email"
        signUpComponent.changeMail(email)
        signUpComponent.singUp()

        advanceUntilIdle()
        assert(signUpComponent.model.value.state is States.Error)
    }

    @Test
    fun `GIVEN correct email WHEN singUp THEN check model`() = runTest {
        val email = "mezhendosina@ya.ru"
        signUpComponent.changeMail(email)
        signUpComponent.singUp()
        advanceUntilIdle()

        verify(authRepository).signUp(email, "", "", "", "", "")
        verify(toCheckCode).invoke()
    }

    @Test
    fun `WHEN onBack THEN invoke onBack`() {
        signUpComponent.onBack()

        verify(onBack).invoke()
    }
}