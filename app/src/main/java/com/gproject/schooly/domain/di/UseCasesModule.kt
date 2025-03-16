package com.gproject.schooly.domain.di

import com.gproject.schooly.domain.auth.usecases.CheckOTPUseCase
import com.gproject.schooly.domain.auth.usecases.ForgotPasswordUseCase
import com.gproject.schooly.domain.auth.usecases.LoginUseCase
import com.gproject.schooly.domain.auth.usecases.ResetPasswordUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { LoginUseCase(get()) }
    single { ForgotPasswordUseCase(get()) }
    single { CheckOTPUseCase(get()) }
    single { ResetPasswordUseCase(get()) }
}