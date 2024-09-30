package ru.sangel.zaya.presentation.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.sangel.zaya.presentation.utils.coroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

abstract class BaseComponent(
    componentContext: ComponentContext,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : ComponentContext by componentContext {

    internal val coroutineScope = CoroutineScope(SupervisorJob() + coroutineContext)

    private val exceptionHandler = coroutineExceptionHandler { errorMessage ->
        onException(errorMessage)
    }

    abstract fun onException(error: String?)

    internal fun CoroutineScope.launchWithExceptionHandler(block: suspend CoroutineScope.() -> Unit) {
        launch(exceptionHandler, block = block)
    }
}