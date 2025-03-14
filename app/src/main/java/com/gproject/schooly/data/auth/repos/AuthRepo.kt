package com.gproject.schooly.data.auth.repos

import com.gproject.schooly.core.network.NetworkHelper
import com.gproject.schooly.data.auth.dataSource.AuthRemoteDataSource
import com.gproject.schooly.domain.auth.repos.IAuthRepo
import com.gproject.schooly.domain.auth.usecases.LoginParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepo(
    private val dataSource: AuthRemoteDataSource, private val networkHelper: NetworkHelper
) : IAuthRepo {
    override suspend fun signIn(params: LoginParams): Flow<Unit?> = flow {
        val result = networkHelper.processSafeCall<Unit> {
            dataSource.signIn(params)
        }
        emit(result)

    }
}