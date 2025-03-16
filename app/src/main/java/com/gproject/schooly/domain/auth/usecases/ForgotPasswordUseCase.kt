package com.gproject.schooly.domain.auth.usecases

import com.gproject.schooly.core.usecases.IBaseUseCase
import com.gproject.schooly.domain.auth.repos.IAuthRepo
import kotlinx.coroutines.flow.Flow

class ForgotPasswordUseCase(private val repo: IAuthRepo) : IBaseUseCase<String?, String> {
    override suspend operator fun invoke(params: String): Flow<String?> {
        return repo.forgotPassword(params)
    }
}