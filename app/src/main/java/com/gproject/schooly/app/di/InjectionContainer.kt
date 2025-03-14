package com.gproject.schooly.app.di

import android.content.Context
import com.gproject.schooly.core.network.di.networkModule
import com.gproject.schooly.core.preferences.di.preferencesModule
import com.gproject.schooly.core.utils.di.utilsModule
import com.gproject.schooly.data.di.remoteDataSourcesModule
import com.gproject.schooly.data.di.reposModule
import com.gproject.schooly.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

fun Context.initDI() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(getKoinModules())
    }
}


private fun getKoinModules(): List<Module> = listOf(
    preferencesModule,
    utilsModule,
    networkModule,
    remoteDataSourcesModule,
    reposModule,
    useCasesModule,
    viewModelsModule,
)