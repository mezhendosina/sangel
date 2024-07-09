package ru.sangel.android.ui.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ru.sangel.presentation.entities.States

@Composable
fun ErrorState(
    state: States,
    snackbarHostState: SnackbarHostState,
) {
    if (state is States.Error) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar(state.message)
        }
    }
}
