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
                state = States.Loaded,
                email = email,
            ),
        )

    override val model: Value<SignUpComponent.Model> = _model

    override fun changeName(name: String) {
        _model.update {
            SignUpComponent.Model(
                name = name,
                phone = it.phone,
                state = it.state,
                email = it.email,
            )
        }
    }

    override fun changePhone(phone: String) {
        _model.update {
            SignUpComponent.Model(
                name = it.name,
                phone = phone,
                email = it.email,
                state = it.state,
            )
        }
    }

    override fun changeMail(mail: String) {
        _model.update {
            SignUpComponent.Model(
                name = it.name,
                phone = it.phone,
                email = mail,
                state = it.state,
            )
        }
    }

    override fun singUp() {
        CoroutineScope(Dispatchers.IO).launch() {
            with(model.value) {
                if (email.isValidEmail()) {
                    authRepository.signUp(
                        email = email,
                        password = "Deprecated",
                        name = name,
                        surname = "Deprecated",
                    )
                    withContext(Dispatchers.Main) {
                        toCheckCode.invoke()
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
