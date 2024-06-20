package ru.sangel.presentation.components.login.signIn

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ru.sangel.data.auth.AuthRepository
import ru.sangel.data.settings.AppPrefs
import ru.sangel.presentation.entities.States
import ru.sangel.presentation.utils.coroutineExceptionHandler

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultSignInComponent(
    private val authRepository: AuthRepository,
    private val appPrefs: AppPrefs,
    private val componentContext: ComponentContext,
    private val toCheckCode: () -> Unit,
    private val toSignUp: (email: String) -> Unit,
) : SignInComponent,
    ComponentContext by componentContext {
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
        CoroutineScope(Dispatchers.Main).launch(coroutineExceptionHandler) {
            authRepository.signIn(_model.value.email)
            toCheckCode.invoke()
        }
    }

    override fun toSignUp() = toSignUp(model.value.email)

    override fun toCheckCode() {
        toCheckCode.invoke()
    }
}
