package com.gproject.schooly.data.di

import com.gproject.schooly.data.auth.dataSource.AuthRemoteDataSource
import org.koin.dsl.module

val remoteDataSourcesModule = module {
    single { AuthRemoteDataSource(get()) }
}