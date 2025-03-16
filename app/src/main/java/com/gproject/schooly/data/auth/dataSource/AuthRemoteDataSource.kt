package com.gproject.schooly.data.auth.dataSource

import com.gproject.schooly.domain.auth.usecases.CheckOTPParams
import com.gproject.schooly.domain.auth.usecases.LoginParams
import com.gproject.schooly.domain.auth.usecases.ResetPasswordParams
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class AuthRemoteDataSource(private val client: HttpClient) {

    suspend fun signIn(params: LoginParams): HttpResponse {
        return client.post("auth/login") {
            setBody(params)
        }
    }

    suspend fun forgotPassword(email: String): HttpResponse {
        return client.post("auth/forgot-password") {
            setBody(mapOf("email" to email))
        }
    }

    suspend fun checkOTP(params: CheckOTPParams): HttpResponse {
        return client.post("auth/verify-code") {
            setBody(params)

        }
    }

    suspend fun resetPassword(params: ResetPasswordParams): HttpResponse {
        return client.post("auth/reset-password") {
            setBody(params)
        }
    }

}