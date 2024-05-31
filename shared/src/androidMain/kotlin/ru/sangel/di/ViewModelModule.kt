package ru.sangel.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sangel.presentation.MainViewModel
import ru.sangel.presentation.map.MapViewModel

val viewModelModule =
    module {
        viewModelOf(::MapViewModel)
        viewModelOf(::MainViewModel)
    }
