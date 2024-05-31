package ru.sangel.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.sangel.app.domain.MapUseCase

val useCaseModule =
    module {
        factoryOf(::MapUseCase)
    }
