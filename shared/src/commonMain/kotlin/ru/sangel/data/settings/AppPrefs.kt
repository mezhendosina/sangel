package ru.sangel.data.settings

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

interface AppPrefs {
    suspend fun <T> getValue(
        key: Preferences.Key<T>,
        defaultValue: T? = null,
    ): Flow<T?>

    suspend fun <T> setValue(
        key: Preferences.Key<T>,
        value: T,
    )

    suspend fun isAuthtorized(): Boolean

    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val TOKEN = stringPreferencesKey("token")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
        val SHOW_LOCATION_TO = stringPreferencesKey("show_location_to")
    }
}
