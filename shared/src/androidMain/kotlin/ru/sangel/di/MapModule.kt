package ru.sangel.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sangel.app.data.map.MapKitRepository
import ru.sangel.app.data.map.MapKitRepositoryImpl

val mapModule =
    module {
        singleOf(::MapKitRepositoryImpl) bind MapKitRepository::class
    }
