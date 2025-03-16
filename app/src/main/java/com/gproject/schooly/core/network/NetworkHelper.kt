package com.gproject.schooly.core.network

import androidx.datastore.core.IOException
import com.gproject.schooly.core.models.Result
import com.gproject.schooly.core.network.exceptions.GeneralHttpException
import com.gproject.schooly.core.network.exceptions.NetworkException
import com.gproject.schooly.core.network.exceptions.UnauthorizedException
import com.gproject.schooly.core.preferences.preferenceManager.PreferencesManager
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

class NetworkHelper(private val preferenceManager: PreferencesManager) {
    internal suspend inline fun <reified T> processSafeCall(networkCall: () -> HttpResponse): T? {
        val response = try {
            networkCall.invoke()
        } catch (throwable: IOException) {
            throw NetworkException(throwable.message)
        } catch (throwable: Throwable) {
            throw UnknownError(throwable.message)
        }
        return handleResponse(response)
    }


    internal suspend inline fun <reified T> handleResponse(response: HttpResponse): T? {
        when (response.status.value) {
            in 200..299 -> {
                val dataPayload = response.body<Result<T>>()

                if (dataPayload.token != null) preferenceManager.saveEncrypted(
                    "token", dataPayload.token
                )

                if (dataPayload.message != null) return dataPayload.message as T?
                return dataPayload.data
            }

            401 -> throw UnauthorizedException(errorMessage = response.body<Result<T>>().message)
            in 400..499 -> {
                val errorBody = response.body<Result<T>>()
                throw GeneralHttpException(
                    errorMsg = errorBody.message, validationErrors = errorBody.errors
                )
            }

            in 500..599 -> throw UnknownError("Server Error")
            else -> throw UnknownError("Unknown Exception")
        }
    }
}