package com.gproject.schooly.app.di

import com.gproject.schooly.presentation.auth.viewmodel.LoginViewModel
import com.gproject.schooly.presentation.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SplashViewModel(get()) }
}