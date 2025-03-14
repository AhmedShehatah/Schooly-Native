package com.gproject.schooly.core.preferences.dataStore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface ILocalDataStore {
    fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> savePreference(key: Preferences.Key<T>, value: T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun clearAllPreference()
}