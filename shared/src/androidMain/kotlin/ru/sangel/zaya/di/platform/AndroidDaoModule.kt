package ru.sangel.zaya.di.platform

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.sangel.zaya.data.AppDatabase
import ru.sangel.zaya.data.getDatabase

actual fun daoModule(): Module =
    module {
        single<AppDatabase> {
            getDatabase(get())
        }
    }
