package com.gproject.schooly.core.network.di

import com.gproject.schooly.core.network.NetworkHelper
import com.gproject.schooly.core.network.client.provideCioClient
import org.koin.dsl.module

val networkModule = module {
    single { provideCioClient(get()) }
    single { NetworkHelper(get()) }
}