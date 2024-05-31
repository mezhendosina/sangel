package ru.sangel.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.sangel.data.AppDatabase
import ru.sangel.data.getDatabase

actual fun daoModule(): Module =
    module {
        single<AppDatabase> {
            getDatabase(get())
        }
    }
