package com.gproject.schooly.core.preferences.sharedPreferences

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocalSharedPreference(val sharedPreference: EncryptedSharedPreferences) {

    inline fun <reified T> getPreference(key: String, defaultValue: T): T? {
        return sharedPreference.getValue(key, defaultValue)

    }

    inline fun <reified T> savePreference(key: String, value: T) {
        sharedPreference.edit().apply { putValue(key, value) }.apply()
    }

    fun removePreference(key: String) {
        sharedPreference.edit().remove(key).apply()
    }

    fun clearAllPreference() {
        sharedPreference.edit().clear().apply()
    }

    inline fun <reified T> SharedPreferences.Editor.putValue(key: String, value: T) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
            else -> Json.encodeToString(value).also { putString(key, it) }
        }
    }

    inline fun <reified T> SharedPreferences.getValue(key: String, defaultValue: T): T? {
        return when (defaultValue) {
            is String -> getString(key, defaultValue) as T
            is Int -> getInt(key, defaultValue) as T
            is Boolean -> getBoolean(key, defaultValue) as T
            is Float -> getFloat(key, defaultValue) as T
            is Long -> getLong(key, defaultValue) as T
            else -> getString(key, Json.encodeToString(defaultValue))?.let {
                Json.decodeFromString(
                    it
                )
            }
        }
    }
}