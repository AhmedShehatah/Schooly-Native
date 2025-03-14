package com.gproject.schooly.app.di

import com.gproject.schooly.presentation.auth.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        LoginViewModel(get())
    }
}