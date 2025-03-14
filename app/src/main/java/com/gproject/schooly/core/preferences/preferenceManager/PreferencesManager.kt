package com.gproject.schooly.core.preferences.preferenceManager

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gproject.schooly.core.preferences.dataStore.ILocalDataStore
import com.gproject.schooly.core.preferences.sharedPreferences.LocalSharedPreference
import com.gproject.schooly.core.utils.SerializerUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("UNCHECKED_CAST")
class PreferencesManager(
    val localDataStore: ILocalDataStore,
    val localSharedPreferences: LocalSharedPreference,
    val serializer: SerializerUtils
) {

    inline fun <reified T> saveEncrypted(key: String, value: T) {
        localSharedPreferences.savePreference(key, value)
    }

     inline fun <reified T> getEncrypted(key: String, defaultValue: T): T? {
        return localSharedPreferences.getPreference(key, defaultValue)
    }

    fun removeEncrypted(key: String) {
        localSharedPreferences.removePreference(key)
    }

    fun clearAllEncrypted() {
        localSharedPreferences.clearAllPreference()
    }

    suspend inline fun <reified T> saveUnEncrypted(key: String, value: T) {
        when (value) {
            is String -> localDataStore.savePreference(stringPreferencesKey(key), value)
            is Int -> localDataStore.savePreference(intPreferencesKey(key), value)
            is Boolean -> localDataStore.savePreference(booleanPreferencesKey(key), value)
            is Float -> localDataStore.savePreference(floatPreferencesKey(key), value)
            is Long -> localDataStore.savePreference(longPreferencesKey(key), value)
            is Double -> localDataStore.savePreference(doublePreferencesKey(key), value)
            else -> localDataStore.savePreference(
                stringPreferencesKey(key), serializer.encodeToString(value) ?: ""
            )
        }
    }

    inline fun <reified T> getUnEncrypted(key: String, defaultValue: T): Flow<T> {
        val flow = when (defaultValue) {
            is String -> localDataStore.getPreference(stringPreferencesKey(key), defaultValue)
            is Int -> localDataStore.getPreference(intPreferencesKey(key), defaultValue)
            is Boolean -> localDataStore.getPreference(booleanPreferencesKey(key), defaultValue)
            is Float -> localDataStore.getPreference(floatPreferencesKey(key), defaultValue)
            is Long -> localDataStore.getPreference(longPreferencesKey(key), defaultValue)
            is Double -> localDataStore.getPreference(doublePreferencesKey(key), defaultValue)
            else -> localDataStore.getPreference(
                stringPreferencesKey(key), serializer.encodeToString(defaultValue) ?: ""
            ).map { serializer.decodeFromString<T>(it) }
        }
        return flow as Flow<T>
    }

    suspend fun removeUnEncrypted(key: String) {
        localDataStore.removePreference(stringPreferencesKey(key))
    }

    suspend fun clearAllUnEncrypted() {
        localDataStore.clearAllPreference()
    }
}