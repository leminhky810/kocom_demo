package com.minhky.itnews.network

import com.minhky.itnews.network.model.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton




@Singleton
class KtorNetworkDataSource  @Inject constructor(private val httpClient: HttpClient): UserNetworkDataSource {
    override suspend fun getUsers(numberOfUser : Int, since : Int) : List<UserResponse> {
        return httpClient.get("users") {
            url {
                parameters.append("per_page", numberOfUser.toString())
                parameters.append("since", since.toString())
            }
        }.body<List<UserResponse>>()
    }
}