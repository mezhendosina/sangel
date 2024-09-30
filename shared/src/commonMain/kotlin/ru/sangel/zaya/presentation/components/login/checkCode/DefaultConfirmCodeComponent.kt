package ru.sangel.zaya.presentation.components.login.checkCode

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sangel.zaya.data.auth.AuthRepository
import ru.sangel.zaya.presentation.entities.States
import ru.sangel.zaya.presentation.utils.coroutineExceptionHandler

class DefaultConfirmCodeComponent(
    private val authRepository: AuthRepository,
    private val componentContext: ComponentContext,
    private val toMain: () -> Unit,
) : ConfirmCodeComponent,
    ComponentContext by componentContext {
    private val _model =
        MutableValue(
            ConfirmCodeComponent.Model(
                "",
                30,
                States.Loaded,
            ),
        )

    private val exceptionHandler =
        coroutineExceptionHandler { error ->
            if (error == null) return@coroutineExceptionHandler

            _model.update {
                ConfirmCodeComponent.Model(it.code, it.timer, States.Error(error))
            }
        }

    override val model: Value<ConfirmCodeComponent.Model> = _model

    override fun onCodeChanges(code: String) {
        _model.update {
            ConfirmCodeComponent.Model(code, it.timer, it.state)
        }
    }

    override fun toMain() {
        CoroutineScope(Dispatchers.IO).launch(exceptionHandler) {
            authRepository.otp(_model.value.code)
            withContext(Dispatchers.Main) {
                toMain.invoke()
            }
        }
    }
}
