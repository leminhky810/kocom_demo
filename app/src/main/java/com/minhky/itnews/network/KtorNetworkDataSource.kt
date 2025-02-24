package com.minhky.itnews.network

import com.minhky.itnews.network.model.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import javax.inject.Inject
import javax.inject.Singleton



const val token = "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" // Thay bằng token của bạn

@Singleton
class KtorNetworkDataSource  @Inject constructor(private val httpClient: HttpClient): UserNetworkDataSource {
    override suspend fun getUsers(numberOfUser : Int, since : Int?) : List<UserResponse> {
        return try {
            httpClient.get("users") {
                url {
                    parameters.append("per_page", numberOfUser.toString())
                    parameters.append("since", since.toString())
                }
            }.body<List<UserResponse>>()
        }
        catch (ex: Exception) {
            emptyList<UserResponse>()
        }
    }
}