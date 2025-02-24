package com.minhky.itnews.data

import androidx.paging.PagingData
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.model.User
import com.minhky.itnews.network.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface ListUserRepository {
     fun fetchUser() : Flow<PagingData<User>>
}