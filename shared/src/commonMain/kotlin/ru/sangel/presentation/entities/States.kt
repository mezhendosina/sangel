package ru.sangel.presentation.entities

sealed class States {
    object Loading : States()

    object Loaded : States()

    class Error(
        val message: String,
    ) : States()
}
