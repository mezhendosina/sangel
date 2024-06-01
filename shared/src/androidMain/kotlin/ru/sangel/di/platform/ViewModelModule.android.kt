package ru.sangel.di.platform

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.sangel.presentation.MainViewModel
import ru.sangel.presentation.map.MapViewModel

actual fun viewModelModule(): Module =
    module {
        viewModelOf(::MapViewModel)
        viewModelOf(::MainViewModel)
    }
