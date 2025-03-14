package com.gproject.schooly.core.utils

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import timber.log.Timber


object LocaleManager {

    fun changeAppLanguage(language: String) {
        val appLocale = LocaleListCompat.forLanguageTags(language)
        Timber.i(appLocale.toString())
        AppCompatDelegate.setApplicationLocales(appLocale)

    }

    fun getAppLanguage(): String {
        val currentAppLocales = AppCompatDelegate.getApplicationLocales()
        return currentAppLocales[0]?.language ?: "ar"
    }
}