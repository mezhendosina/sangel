package ru.sangel.presentation.components.login.signIn

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sangel.data.auth.AuthRepository
import ru.sangel.data.settings.AppPrefs
import ru.sangel.presentation.entities.States

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultSignInComponent(
    private val authRepository: AuthRepository,
    private val appPrefs: AppPrefs,
    private val componentContext: ComponentContext,
    private val toCheckCode: () -> Unit,
    private val toSignIn: (email: String) -> Unit,
) : SignInComponent, ComponentContext by componentContext {
    private val _model =
        MutableValue<SignInComponent.Model>(
            SignInComponent.Model(
                "",
                States.LOADED,
            ),
        )

    override val model: Value<SignInComponent.Model> = _model

    init {
        CoroutineScope(Dispatchers.Unconfined).launch {
            appPrefs.getValue(AppPrefs.TOKEN).collect {
                if (it != null) {
                }
            }
        }
    }

    override fun onEmailChange(email: String) {
        _model.value = SignInComponent.Model(email, States.LOADED)
    }

    override fun signIn() {
        CoroutineScope(Dispatchers.Main).launch {
            authRepository.signIn(_model.value.email)
            toCheckCode.invoke()

            try {
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println(e.stackTraceToString())
                    toSignIn.invoke(model.value.email)
                }
            }
        }
    }

    override fun toCheckCode() {
        toCheckCode.invoke()
    }
}
