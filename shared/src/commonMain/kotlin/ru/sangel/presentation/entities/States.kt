package ru.sangel.presentation.entities

import com.arkivanov.decompose.value.MutableValue

sealed class States {
    object Loading : States()

    object Loaded : States()

    class Error(
        val message: String,
    ) : States()
}

fun <T: Any> MutableValue<T>.stateLoading() {

}