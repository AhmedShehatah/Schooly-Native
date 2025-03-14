package com.gproject.schooly.core.preferences.dataStore

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "schooly_data_store"
)

class LocalDataStore(context: Context) : ILocalDataStore {
    private val dataStore = context.dataStore
    override fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { preference ->
            val result = preference[key] ?: defaultValue
            result
        }
    }

    override suspend fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataStore.edit { it.remove(key) }
    }

    override suspend fun clearAllPreference() {
        dataStore.edit { it.clear() }

    }

}