package com.gproject.schooly.domain.auth.usecases

import com.gproject.schooly.core.usecases.IBaseUseCase
import com.gproject.schooly.domain.auth.repos.IAuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

class CheckOTPUseCase(private val repo: IAuthRepo) : IBaseUseCase<String?, CheckOTPParams> {
    override suspend operator fun invoke(params: CheckOTPParams): Flow<String?> {
        return repo.checkOTP(params)
    }
}

@Serializable
data class CheckOTPParams(
    val email: String, val code: String
)