package ru.sangel.presentation.utils

import kotlinx.coroutines.CoroutineExceptionHandler

fun coroutineExceptionHandler(messageError: (String?) -> Unit) =
    CoroutineExceptionHandler { coroutineContext, throwable ->
        messageError(throwable.localizedMessage)
    }
