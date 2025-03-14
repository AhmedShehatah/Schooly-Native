package com.gproject.schooly.app

import android.app.Application
import com.gproject.schooly.BuildConfig
import com.gproject.schooly.app.di.initDI
import timber.log.Timber

class SchoolyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}