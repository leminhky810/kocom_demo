package com.minhky.itnews.domain

import com.minhky.itnews.data.ListUserRepository
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.network.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchListUserUseCase @Inject constructor(
    private val listUserRepository: ListUserRepository,
) {
     suspend operator fun invoke(numberOfUser: Int, since: Int): Flow<List<UserEntity>> {
        return listUserRepository.fetchUser(numberOfUser, since)

    }
}
