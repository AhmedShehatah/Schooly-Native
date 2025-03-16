package com.gproject.schooly.domain.auth.repos

import com.gproject.schooly.domain.auth.usecases.CheckOTPParams
import com.gproject.schooly.domain.auth.usecases.LoginParams
import com.gproject.schooly.domain.auth.usecases.ResetPasswordParams
import kotlinx.coroutines.flow.Flow

interface IAuthRepo {

    suspend fun signIn(params: LoginParams): Flow<Unit?>
    suspend fun forgotPassword(email: String): Flow<String?>
    suspend fun checkOTP(params: CheckOTPParams): Flow<String?>
    suspend fun resetPassword(params: ResetPasswordParams): Flow<String?>
}