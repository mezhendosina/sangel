package ru.sangel.zaya.di.platform

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.sangel.zaya.domain.MapUseCase

actual fun useCaseModule(): Module =
    module {
        factoryOf(::MapUseCase)
    }
