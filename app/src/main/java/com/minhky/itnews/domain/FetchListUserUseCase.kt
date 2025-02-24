package com.minhky.itnews.domain

import androidx.paging.PagingData
import com.minhky.itnews.data.ListUserRepository
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.database.model.toUser
import com.minhky.itnews.model.User
import com.minhky.itnews.network.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchListUserUseCase @Inject constructor(
    private val listUserRepository: ListUserRepository,
) {
      operator fun invoke(): Flow<PagingData<User>> {
        return listUserRepository.fetchUser()
    }
}
