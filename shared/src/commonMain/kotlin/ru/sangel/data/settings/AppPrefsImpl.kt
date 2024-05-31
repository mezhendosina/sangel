package ru.sangel.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPrefsImpl(
    private val dataStore: DataStore<Preferences>,
) : AppPrefs {
    override suspend fun <T> getValue(
        key: Preferences.Key<T>,
        defaultValue: T?,
    ): Flow<T?> = dataStore.data.map { it[key] }

    override suspend fun <T> setValue(
        key: Preferences.Key<T>,
        value: T,
    ) {
        dataStore.edit {
            it[key] = value
        }
    }
}
