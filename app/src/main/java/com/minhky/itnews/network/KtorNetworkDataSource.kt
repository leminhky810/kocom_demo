package com.minhky.itnews.network

import com.minhky.itnews.network.model.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import javax.inject.Inject
import javax.inject.Singleton




@Singleton
class KtorNetworkDataSource  @Inject constructor(private val httpClient: HttpClient): UserNetworkDataSource {
    override suspend fun getUsers(page : Int, since : Int) : List<UserResponse> {
        return httpClient.get("users") {
            url {
                parameters.append("per_page", page.toString())
                parameters.append("since", since.toString())
            }
        }.body<List<UserResponse>>()
    }
}