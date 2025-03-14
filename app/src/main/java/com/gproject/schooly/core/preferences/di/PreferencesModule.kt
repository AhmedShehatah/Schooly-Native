package com.gproject.schooly.core.preferences.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.gproject.schooly.core.preferences.dataStore.ILocalDataStore
import com.gproject.schooly.core.preferences.dataStore.LocalDataStore
import com.gproject.schooly.core.preferences.preferenceManager.PreferencesManager
import com.gproject.schooly.core.preferences.sharedPreferences.LocalSharedPreference
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {
    single<ILocalDataStore> { LocalDataStore(androidContext()) }

    single { provideEncryptedSharedPreferences(androidContext()) }

    single { LocalSharedPreference(get()) }

    single { PreferencesManager(get(), get(), get()) }
}

fun provideEncryptedSharedPreferences(context: Context): EncryptedSharedPreferences {
    val masterKeyAlias =
        MasterKey.Builder(context.applicationContext).setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

    return EncryptedSharedPreferences.create(
        context,
        "schooly_shared_preference",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    ) as EncryptedSharedPreferences
}