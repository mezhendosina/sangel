package ru.sangel.zaya.di.common

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import ru.sangel.zaya.BASE_URL
import ru.sangel.zaya.data.auth.AuthRepository
import ru.sangel.zaya.data.settings.AppPrefs

val ktorfitModule =
    module {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json(
                        Json {
                            isLenient = true
                            ignoreUnknownKeys = true
                        },
                    )
                }
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.ALL
                }

                install(Auth) {
                    bearer {
                        loadTokens {
                            val appPrefs = get() as AppPrefs
                            val token =
                                appPrefs.getValue(AppPrefs.ACCESS_TOKEN).first()
                                    ?: return@loadTokens null
                            val refreshToken =
                                appPrefs.getValue(AppPrefs.REFRESH_TOKEN).first()
                                    ?: return@loadTokens null
                            BearerTokens(token, refreshToken)
                        }
                        refreshTokens {
                            val authRepository = get() as AuthRepository
                            val token = authRepository.refreshToken()
                            BearerTokens(
                                token,
                                oldTokens?.refreshToken ?: return@refreshTokens null,
                            )
                        }
                    }
                }
                install(HttpRequestRetry) {
                    retryOnException(2)
                    retryOnServerErrors(maxRetries = 5)
                    exponentialDelay()
                }
                HttpResponseValidator {
                    handleResponseExceptionWithRequest { exception, request ->
                    }
                }
                defaultRequest {
                    contentType(ContentType.Application.Json)
                }
                expectSuccess = true
                followRedirects = true
            }
        }
        single {
            Ktorfit
                .Builder()
                .baseUrl(BASE_URL)
                .httpClient(get<HttpClient>())
                .build()
        }
    }
