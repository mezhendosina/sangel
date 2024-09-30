package ru.sangel.zaya.presentation.components.login.signUp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sangel.zaya.data.auth.AuthRepository
import ru.sangel.zaya.presentation.components.BaseComponent
import ru.sangel.zaya.presentation.entities.States
import ru.sangel.zaya.presentation.utils.coroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

class DefaultSignUpComponent(
    private val email: String,
    private val authRepository: AuthRepository,
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    coroutineContext: CoroutineContext = Dispatchers.IO,
    private val toCheckCode: () -> Unit,
) : SignUpComponent, BaseComponent(componentContext, coroutineContext) {
    private val _model =
        MutableValue(
            SignUpComponent.Model.init(),
        )

    override fun onException(error: String?) {
        TODO("Not yet implemented")
    }

    override val model: Value<SignUpComponent.Model> = _model

    override fun changeName(name: String) = _model.update { it.copy(firstName = name) }

    override fun changePhone(phone: String) = _model.update { it.copy(phoneNumber = phone) }

    override fun changeMail(mail: String) = _model.update { it.copy(email = mail) }

    override fun changePassword(password: String) = _model.update { it.copy(password = password) }

    override fun singUp() {
        coroutineScope.launchWithExceptionHandler {
            with(model.value) {
                _model.update {
                    it.copy(state = States.Loading)
                }
                if (email.isValidEmail()) {
                    authRepository.signUp(
                        email = email,
                        password = password,
                        firstName = firstName,
                        secondName = secondName,
                        middleName = middleName,
                        phone = phoneNumber,
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
