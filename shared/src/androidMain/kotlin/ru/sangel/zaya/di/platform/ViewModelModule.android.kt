package ru.sangel.zaya.di.platform

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.sangel.zaya.presentation.MainViewModel
import ru.sangel.zaya.presentation.map.MapViewModel

actual fun viewModelModule(): Module =
    module {
        viewModelOf(::MapViewModel)
        viewModelOf(::MainViewModel)
    }
