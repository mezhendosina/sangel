package ru.sangel.zaya.di.platform

import com.yandex.mapkit.MapKitFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sangel.zaya.data.map.MapRepository
import ru.sangel.zaya.data.map.MapPoints
import ru.sangel.zaya.data.map.MapPointsImpl
import ru.sangel.zaya.data.map.MapRepositoryImpl

actual fun mapModule(): Module =
    module {
        singleOf(::MapRepositoryImpl) bind MapRepository::class
        singleOf(::MapPointsImpl) bind MapPoints::class
        single { MapKitFactory.getInstance().createLocationManager() }
    }
