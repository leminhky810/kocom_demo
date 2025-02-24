package com.minhky.itnews.network

import com.minhky.itnews.network.model.UserResponse

interface UserNetworkDataSource {
    suspend fun getUsers(numberOfUser : Int, since: Int?) :  List<UserResponse>
}