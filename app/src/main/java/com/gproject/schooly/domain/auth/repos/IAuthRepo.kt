package com.gproject.schooly.domain.auth.repos

import com.gproject.schooly.domain.auth.usecases.LoginParams
import kotlinx.coroutines.flow.Flow

interface IAuthRepo {

    suspend fun signIn(params: LoginParams): Flow<Unit?>
}