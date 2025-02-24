package com.minhky.itnews.pagging

import android.icu.util.TimeUnit
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.minhky.itnews.data.model.toUserEntity
import com.minhky.itnews.database.ITNewsDataBase
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.network.UserNetworkDataSource
import com.minhky.itnews.network.model.UserResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

import kotlinx.io.IOException
import okhttp3.internal.wait

import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserListMediator @Inject constructor(
    private val database: ITNewsDataBase,
    private val networkService: UserNetworkDataSource
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    Log.d("Paging_Log", "LoadType : REFRESH ")
                    1
                }
                LoadType.PREPEND -> {
                    Log.d("Paging_Log", "LoadType : REFRESH ")
                   return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    Log.d("Paging_Log", "LoadType : APPEND ")
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        lastItem.id + 1
                    }
                }
            }
            Log.d("Paging_Log", "Network Call: $loadKey ")
            val response = networkService.getUsers(
                state.config.pageSize, loadKey
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.userDao().deleteByQuery("")
                }
                database.userDao().upsertAll(response.map(UserResponse::toUserEntity))
            }



            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


}
