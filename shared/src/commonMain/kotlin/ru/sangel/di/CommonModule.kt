package ru.sangel.di

import org.koin.dsl.module
import ru.sangel.di.common.componentModule
import ru.sangel.di.common.emergencyModule
import ru.sangel.di.common.ktorfitModule
import ru.sangel.di.common.repoModule
import ru.sangel.di.common.settingsModule
import ru.sangel.di.common.stubSourceModule

fun commonModule() =
    module {
        includes(
            settingsModule,
            ktorfitModule,
            stubSourceModule,
            repoModule,
            componentModule,
            emergencyModule,
        )
    }
