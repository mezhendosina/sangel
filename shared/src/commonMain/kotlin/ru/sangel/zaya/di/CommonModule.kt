package ru.sangel.zaya.di

import org.koin.dsl.module
import ru.sangel.zaya.di.common.componentModule
import ru.sangel.zaya.di.common.ktorfitModule
import ru.sangel.zaya.di.common.repoModule
import ru.sangel.zaya.di.common.settingsModule
import ru.sangel.zaya.di.common.stubSourceModule

fun commonModule() =
    module {
        includes(
            settingsModule,
            ktorfitModule,
            stubSourceModule,
            repoModule,
            componentModule,
        )
    }
