package ru.sangel.di.common

import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.lazyModule
import org.koin.dsl.module
import ru.sangel.presentation.components.root.DefaultRootComponent

val componentModule = module {
    single {
        DefaultRootComponent(it.get(), it.get())
    }
}