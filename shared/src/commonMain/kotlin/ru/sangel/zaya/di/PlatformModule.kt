package ru.sangel.zaya.di

import org.koin.dsl.module
import ru.sangel.zaya.di.platform.daoModule
import ru.sangel.zaya.di.platform.mapModule
import ru.sangel.zaya.di.platform.useCaseModule
import ru.sangel.zaya.di.platform.viewModelModule

val platfromModule =
    module {
        includes(
            daoModule(),
            mapModule(),
            useCaseModule(),
            viewModelModule(),
        )
    }
