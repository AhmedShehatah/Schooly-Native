package com.gproject.schooly.data.auth.dataSource

import com.gproject.schooly.domain.auth.usecases.LoginParams
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
}