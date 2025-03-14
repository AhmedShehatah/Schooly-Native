package com.gproject.schooly.core.utils.di

import com.gproject.schooly.core.utils.SerializerUtils
import org.koin.dsl.module

val utilsModule = module {
    single { SerializerUtils() }
}