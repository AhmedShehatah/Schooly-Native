package com.gproject.schooly.core.usecases

import kotlinx.coroutines.flow.Flow

interface IBaseUseCase<out T, in Params> {
    suspend operator fun invoke(params: Params): Flow<T>
}

interface IBaseUseCaseNoParams<out T> {
    suspend operator fun invoke(): Flow<T>
}