package ru.sangel.di.platform

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.sangel.domain.MapUseCase

actual fun useCaseModule(): Module =
    module {
        factoryOf(::MapUseCase)
    }
