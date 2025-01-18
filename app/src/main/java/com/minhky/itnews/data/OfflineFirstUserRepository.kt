package com.minhky.itnews.data

import com.minhky.itnews.network.UserNetworkDataSource
import com.minhky.itnews.network.model.UserResponse
import javax.inject.Inject

class OfflineFirstUserRepository @Inject constructor (private val userNetworkDataSource: UserNetworkDataSource): ListUserRepository {

    override suspend fun fetchUser(page: Int, since: Int) :  List<UserResponse> {
        return userNetworkDataSource.getUsers(page,since)
    }
}