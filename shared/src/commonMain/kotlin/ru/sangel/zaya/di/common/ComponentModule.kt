package ru.sangel.zaya.di.common

import org.koin.dsl.module
import ru.sangel.zaya.presentation.components.root.DefaultRootComponent

val componentModule = module {
    single {
        DefaultRootComponent(it.get(), it.get())
    }
}