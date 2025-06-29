package app.wallet_monitor.shared

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(prefs: DataStore<Preferences>) {
    private val dataStore: DataStore<Preferences> = prefs

    fun getPrefs(): DataStore<Preferences> {
        return dataStore
    }

    // setters
    suspend fun setString(key: String, value: String) {
        dataStore.edit { preferences ->
            val nameKey = stringPreferencesKey(key)
            preferences[nameKey] = value
        }
    }
    suspend fun setInt(key: String, value: Int) {
        dataStore.edit { preferences ->
            val nameKey = intPreferencesKey(key)
            preferences[nameKey] = value
        }
    }
    suspend fun setBoolean(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            val nameKey = booleanPreferencesKey(key)
            preferences[nameKey] = value
        }
    }

    // getters
    fun getString(key: String): Flow<String> {
        val nameKey = stringPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[nameKey] ?: ""
        }
    }

    fun getInt(key: String): Flow<Int> {
        val nameKey = intPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[nameKey] ?: 0
        }
    }

    fun getBoolean(key: String): Flow<Boolean> {
        val nameKey = booleanPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[nameKey] ?: false
        }
    }
}