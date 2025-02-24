package com.minhky.itnews.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    const val token = "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" // Thay bằng token của bạn

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true        // Enable pretty-printing of JSON
                    isLenient = true          // Allow lenient parsing of JSON
                    ignoreUnknownKeys = true  // Ignore unknown keys in JSON (optional)
                })
            }
            engine {
                // Configure OkHttp if needed
                config {
                    retryOnConnectionFailure(true)
                }
            }
            // Optionally configure timeouts
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 15_000
                socketTimeoutMillis = 15_000
            }
            // Default request configuration
            defaultRequest {
                url("https://api.github.com/users") // Set the base URL
                header("Accept", "application/json;charset=utf-8 ") // Add common headers if needed
                header(HttpHeaders.Authorization, "token $token")
            }
        }
    }
}