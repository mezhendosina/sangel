package ru.sangel.di

import org.koin.dsl.module
import ru.sangel.di.platform.daoModule
import ru.sangel.di.platform.mapModule
import ru.sangel.di.platform.useCaseModule
import ru.sangel.di.platform.viewModelModule

val platfromModule =
    module {
        includes(
            daoModule(),
            mapModule(),
            useCaseModule(),
            viewModelModule(),
        )
    }
