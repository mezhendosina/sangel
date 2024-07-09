package ru.sangel.presentation.components.login.signIn

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.sangel.data.auth.AuthRepository
import ru.sangel.presentation.entities.States
import ru.sangel.presentation.utils.coroutineExceptionHandler

class DefaultSignInComponent(
    private val authRepository: AuthRepository,
    private val componentContext: ComponentContext,
    private val toCheckCode: () -> Unit,
    private val toSignUp: (email: String) -> Unit,
) : SignInComponent,
    ComponentContext by componentContext {
    private val _model =
        MutableValue<SignInComponent.Model>(
            SignInComponent.Model(
                "",
                States.Loaded,
            ),
        )
    private val exceptionHandler =
        coroutineExceptionHandler { errorMessage ->
            if (errorMessage == null) return@coroutineExceptionHandler

            _model.update {
                it.copy(
                    state = States.Error(errorMessage),
                )
            }
        }

    override val model: Value<SignInComponent.Model> = _model

    override fun onEmailChange(email: String) {
        _model.value = SignInComponent.Model(email, States.Loaded)
    }

    override fun signIn() {
        CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
            authRepository.signIn(_model.value.email)
            toCheckCode.invoke()
        }
    }

    override fun toSignUp() = toSignUp(model.value.email)

    override fun toCheckCode() {
        toCheckCode.invoke()
    }
}
