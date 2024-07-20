package ru.sangel.di.platform

import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sangel.app.data.map.MapKitRepository
import ru.sangel.data.map.MapKitRepositoryImpl
import ru.sangel.data.map.MapPoints
import ru.sangel.data.map.MapPointsImpl

actual fun mapModule(): Module =
    module {
        singleOf(::MapKitRepositoryImpl) bind MapKitRepository::class
        singleOf(::MapPointsImpl) bind MapPoints::class
        single { SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED) }
        single { MapKitFactory.getInstance().createLocationManager() }
    }
