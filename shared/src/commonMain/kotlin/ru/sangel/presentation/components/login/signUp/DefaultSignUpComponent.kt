package ru.sangel.presentation.components.login.signUp

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sangel.data.auth.AuthRepository
import ru.sangel.presentation.entities.States
import ru.sangel.presentation.utils.coroutineExceptionHandler

class DefaultSignUpComponent(
    private val email: String,
    private val authRepository: AuthRepository,
    private val onBack: () -> Unit,
    private val toCheckCode: () -> Unit,
) : SignUpComponent {
    private val _model =
        MutableValue(
            SignUpComponent.Model(
                name = "",
                phone = "",
                email = email,
                password = "",
                state = States.Loaded,
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
    override val model: Value<SignUpComponent.Model> = _model

    override fun changeName(name: String) = _model.update { it.copy(name = name) }

    override fun changePhone(phone: String) = _model.update { it.copy(phone = phone) }

    override fun changeMail(mail: String) = _model.update { it.copy(email = mail) }

    override fun changePassword(password: String) = _model.update { it.copy(password = password) }

    override fun singUp() {
        CoroutineScope(Dispatchers.IO).launch(exceptionHandler) {
            with(model.value) {
                _model.update {
                    it.copy(state = States.Loading)
                }
                if (email.isValidEmail()) {
                    authRepository.signUp(
                        email = email,
                        password = password,
                        name = name,
                        surname = "Deprecated",
                    )
                    withContext(Dispatchers.Main) {
                        toCheckCode.invoke()
                    }
                } else {
                    _model.update {
                        it.copy(state = States.Error("Email не соответсвует формату"))
                    }
                }
            }
        }
    }

    override fun onBack() {
        onBack.invoke()
    }

    private fun String.isValidEmail(): Boolean = true
}
