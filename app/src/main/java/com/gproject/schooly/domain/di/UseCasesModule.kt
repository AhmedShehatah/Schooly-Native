package com.gproject.schooly.domain.di

import com.gproject.schooly.domain.auth.usecases.LoginUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { LoginUseCase(get()) }
}