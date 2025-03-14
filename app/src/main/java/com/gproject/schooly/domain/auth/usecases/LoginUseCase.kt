package com.gproject.schooly.domain.auth.usecases

import com.gproject.schooly.core.usecases.IBaseUseCase
import com.gproject.schooly.domain.auth.repos.IAuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

class LoginUseCase(private val repo: IAuthRepo) : IBaseUseCase<Unit?, LoginParams> {
    override suspend operator fun invoke(params: LoginParams): Flow<Unit?> {
        return repo.signIn(params)
    }
}

@Serializable
data

class LoginParams(
    val email: String, val password: String
)