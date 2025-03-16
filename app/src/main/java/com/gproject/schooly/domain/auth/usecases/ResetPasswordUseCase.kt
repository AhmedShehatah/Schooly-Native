package com.gproject.schooly.domain.auth.usecases

import com.gproject.schooly.core.usecases.IBaseUseCase
import com.gproject.schooly.domain.auth.repos.IAuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

class ResetPasswordUseCase(private val repo: IAuthRepo) :
    IBaseUseCase<String?, ResetPasswordParams> {
    override suspend operator fun invoke(params: ResetPasswordParams): Flow<String?> {
        return repo.resetPassword(params)
    }
}

@Serializable
data class ResetPasswordParams(
    val email: String, val newPassword: String,
)