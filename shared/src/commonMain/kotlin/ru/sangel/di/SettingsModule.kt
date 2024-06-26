package ru.sangel.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.plus
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.dsl.single
import ru.sangel.data.dataStorePreferences
import ru.sangel.data.settings.AppPrefs
import ru.sangel.data.settings.AppPrefsImpl
import ru.sangel.presentation.components.root.DefaultRootComponent

val settingsModule =
    module {
        single {
            dataStorePreferences(
                get(),
                corruptionHandler = null,
                coroutineScope =
                    CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate) + Dispatchers.IO,
                migrations = emptyList(),
            )
        }
        singleOf(::AppPrefsImpl) bind AppPrefs::class
        single {
            val appPrefs = get() as AppPrefs
            return@single CoroutineScope(Dispatchers.IO).async {
                if (appPrefs.getValue(AppPrefs.TOKEN)
                        .first().isNullOrEmpty()
                ) {
                    DefaultRootComponent.TopConfig.Login
                } else {
                    DefaultRootComponent.TopConfig.Main
                }
            }
        }
    }
