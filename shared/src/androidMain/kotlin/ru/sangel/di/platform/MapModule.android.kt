package ru.sangel.di.platform

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sangel.app.data.map.MapKitRepository
import ru.sangel.app.data.map.MapKitRepositoryImpl

actual fun mapModule(): Module =
    module {
        singleOf(::MapKitRepositoryImpl) bind MapKitRepository::class
    }