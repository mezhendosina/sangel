package ru.sangel.di

import org.koin.dsl.module
import ru.sangel.di.common.ktorfitModule
import ru.sangel.di.common.repoModule
import ru.sangel.di.common.settingsModule
import ru.sangel.di.common.sourceModule

fun commonModule() =
    module {
        includes(
            settingsModule,
            ktorfitModule,
            sourceModule,
            repoModule,
        )
    }
