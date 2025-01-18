package com.minhky.itnews.domain

import com.minhky.itnews.data.ListUserRepository
import com.minhky.itnews.network.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchListUserUseCase @Inject constructor(
    private val recentSearchRepository: ListUserRepository,
) {
    operator fun invoke(page: Int, since: Int): Flow<List<UserResponse>> {
        return flow {
            emit(recentSearchRepository.fetchUser(page, since))
        }
    }
}