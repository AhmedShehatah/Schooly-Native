package com.gproject.schooly.core.network.client

import com.gproject.schooly.BuildConfig
import com.gproject.schooly.core.preferences.preferenceManager.PreferencesManager
import com.gproject.schooly.core.utils.LocaleManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import timber.log.Timber

@OptIn(ExperimentalSerializationApi::class)
fun provideCioClient(
    preferencesManager: PreferencesManager
): HttpClient {

    return HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }

        engine {
            requestTimeout = 60_000L
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.i("Ktor Logging: %s", message)
                }

            }
            level = LogLevel.ALL
        }
        install(ResponseObserver) {
            onResponse { response ->
                Timber.i("HTTPS Status: %s", response)
            }
        }


        install(Auth) {
            bearer {
                loadTokens {
                    runBlocking {
                        val token = preferencesManager.getEncrypted("token", "")
                        BearerTokens(accessToken = token ?: "", refreshToken = "")
                    }
                }

            }
        }


        defaultRequest {
            url(BuildConfig.BASE_URL)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            header(HttpHeaders.AcceptLanguage, LocaleManager.getAppLanguage())

        }


    }


}