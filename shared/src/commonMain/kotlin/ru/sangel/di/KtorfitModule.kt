package ru.sangel.di

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
import ru.sangel.BASE_URL
import ru.sangel.data.auth.AuthRepository
import ru.sangel.data.settings.AppPrefs

val ktorfitModule =
    module {
        single {
            val ktorfitClient =
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
                                    appPrefs.getValue(AppPrefs.TOKEN).first()
                                        ?: return@loadTokens null

                                BearerTokens(token, "")
                            }
                            refreshTokens {
                                val authRepository = get() as AuthRepository
                                val appPrefs = get() as AppPrefs
                                authRepository.signIn(
                                    appPrefs.getValue(AppPrefs.EMAIL).first()
                                        ?: return@refreshTokens null,
                                )

                                BearerTokens(
                                    appPrefs.getValue(AppPrefs.TOKEN).first { it != null }
                                        ?: "",
                                    "",
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
            Ktorfit.Builder()
                .baseUrl(BASE_URL)
                .httpClient(ktorfitClient)
                .build()
        }
    }
