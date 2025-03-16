package com.gproject.schooly.data.auth.repos

import com.gproject.schooly.core.network.NetworkHelper
import com.gproject.schooly.data.auth.dataSource.AuthRemoteDataSource
import com.gproject.schooly.domain.auth.repos.IAuthRepo
import com.gproject.schooly.domain.auth.usecases.CheckOTPParams
import com.gproject.schooly.domain.auth.usecases.LoginParams
import com.gproject.schooly.domain.auth.usecases.ResetPasswordParams
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

    override suspend fun forgotPassword(email: String): Flow<String?> = flow {
        val result = networkHelper.processSafeCall<String> {
            dataSource.forgotPassword(email)
        }
        emit(result)

    }

    override suspend fun resetPassword(params: ResetPasswordParams): Flow<String?> = flow {
        val result = networkHelper.processSafeCall<String> {
            dataSource.resetPassword(params = params)
        }
        emit(result)

    }

    override suspend fun checkOTP(params: CheckOTPParams): Flow<String?> = flow {
        val result = networkHelper.processSafeCall<String> {
            dataSource.checkOTP(params)
        }
        emit(result)
    }
}