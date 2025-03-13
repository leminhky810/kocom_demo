package com.minhky.itnews.data


import android.graphics.pdf.PdfDocument.Page
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map

import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.database.model.toUser
import com.minhky.itnews.model.User

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class OfflineFirstUserRepository
@Inject constructor(
    private val pager : Pager<Int, UserEntity>,
) : ListUserRepository {

    override fun fetchUser(): Flow<PagingData<User>> {
        return pager.flow
            .map { pagingData ->
                pagingData.map { user ->
                    user.toUser()
                }
            }


    }
}