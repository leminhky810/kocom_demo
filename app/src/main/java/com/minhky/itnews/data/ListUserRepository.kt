package com.minhky.itnews.data

import com.minhky.itnews.network.model.UserResponse

interface ListUserRepository {
    suspend fun fetchUser(page: Int, since : Int) :  List<UserResponse>
}