package com.gproject.schooly.data.di

import com.gproject.schooly.data.auth.repos.AuthRepo
import com.gproject.schooly.domain.auth.repos.IAuthRepo
import org.koin.dsl.module

val reposModule = module {
    single<IAuthRepo> { AuthRepo(get(), get()) }
}