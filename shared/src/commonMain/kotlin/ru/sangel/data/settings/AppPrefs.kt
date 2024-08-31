package ru.sangel.data.settings

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
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

    suspend fun isAuthorized(): Boolean

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val SHOW_LOCATION_TO = stringPreferencesKey("show_location_to")

        val EMERGENCY_PHONE_NUMBER = stringPreferencesKey("emergency_phone_number")
        val EMERGENCY_INCOMING_PHONE_NUMBER =
            stringPreferencesKey("emergency_incoming_phone_number")
        val FETCH_EMERGENCY_NUMBERS = booleanPreferencesKey("fetch_emergency_numbers")
    }
}
