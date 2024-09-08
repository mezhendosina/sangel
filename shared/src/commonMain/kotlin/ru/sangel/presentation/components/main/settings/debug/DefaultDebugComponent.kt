package ru.sangel.presentation.components.main.settings.debug

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import ru.sangel.data.messages.MessagesRepository
import ru.sangel.data.settings.AppPrefs
import ru.sangel.presentation.entities.States
import ru.sangel.presentation.utils.coroutineExceptionHandler
import ru.sangel.presentation.utils.showLogToast

class DefaultDebugComponent(
    val componentContext: ComponentContext,
) : DebugComponent,
    KoinComponent,
    ComponentContext by componentContext {
    private val messagesRepository by inject<MessagesRepository>()
    private val appPrefs by inject<AppPrefs>()

    private val _model = MutableValue(DebugComponent.Model("", "", true, States.Loaded))
    override val model: Value<DebugComponent.Model> = _model

    override suspend fun observeNumbers() {
        withContext(Dispatchers.IO) {
            appPrefs.getValue(AppPrefs.EMERGENCY_PHONE_NUMBER).collect { number ->
                withContext(Dispatchers.Main) { _model.update { it.copy(phoneNubmer = number.toString()) } }
            }
        }
        withContext(Dispatchers.IO) {
            appPrefs.getValue(AppPrefs.EMERGENCY_INCOMING_PHONE_NUMBER).collect { number ->
                withContext(Dispatchers.Main) { _model.update { it.copy(incomingPhoneNumber = number.toString()) } }
            }
        }
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val coroutineExceptionHandler =
        coroutineExceptionHandler {
            println(it)
            _model.update { model1 ->
                model1.copy(
                    states = States.Error(it.toString()),
                )
            }
            get<Context>().showLogToast(it ?: "")
        }

    override fun smsTofavorites() {
        coroutineScope.launch(coroutineExceptionHandler) {
            setLoading()
            messagesRepository.sendMessageToFavorites()
            setLoaded()
        }
    }

    override fun smsTo112() {
        coroutineScope.launch {
            messagesRepository.sendMessageToPolice(true)
            setLoaded()
        }
    }

    override fun notificationToNear() {
        coroutineScope.launch(coroutineExceptionHandler) {
            setLoading()
            messagesRepository.sendMessagesToNearUsers()
            setLoaded()
        }
    }

    override fun updateNumber(number: String) = _model.update { it.copy(phoneNubmer = number) }

    override fun updateIncomingNumber(number: String) = _model.update { it.copy(incomingPhoneNumber = number) }

    override fun saveNumber() {
        coroutineScope.launch(coroutineExceptionHandler) {
            appPrefs.setValue(AppPrefs.EMERGENCY_PHONE_NUMBER, model.value.phoneNubmer)
        }
    }

    override fun saveIncomingNumber() {
        coroutineScope.launch(coroutineExceptionHandler) {
            appPrefs.setValue(
                AppPrefs.EMERGENCY_INCOMING_PHONE_NUMBER,
                model.value.incomingPhoneNumber,
            )
        }
    }

    override fun changeUpdatingNumbers(boolean: Boolean) {
        coroutineScope.launch(coroutineExceptionHandler) {
            appPrefs.setValue(AppPrefs.FETCH_EMERGENCY_NUMBERS, boolean)
            _model.update { it.copy(updateNumbers = boolean) }
        }
    }

    private suspend fun setLoading() = withContext(Dispatchers.Main) { _model.update { it.copy(states = States.Loading) } }

    private suspend fun setLoaded() = withContext(Dispatchers.Main) { _model.update { it.copy(states = States.Loaded) } }
}
