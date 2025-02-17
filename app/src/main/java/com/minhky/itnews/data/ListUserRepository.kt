package com.minhky.itnews.data

import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.model.User
import com.minhky.itnews.network.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface ListUserRepository {
    suspend fun fetchUser(numberOfUser: Int, since : Int) : Flow<List<User>>
}