package com.minhky.itnews.pagging.di

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.minhky.itnews.database.ITNewsDataBase
import com.minhky.itnews.database.UserDao
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.network.UserNetworkDataSource
import com.minhky.itnews.network.model.UserResponse
import com.minhky.itnews.pagging.UserListMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PaggingModule {

    @Singleton
    @Provides
    fun provideRemoteMediator(database: ITNewsDataBase, networkService: UserNetworkDataSource) =
        UserListMediator(
            database = database,
            networkService = networkService
        )

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(itDatabase: ITNewsDataBase, networkService:UserNetworkDataSource): Pager<Int, UserEntity>  {
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = UserListMediator(
                database = itDatabase,
                networkService = networkService
            ),
            pagingSourceFactory = {
                itDatabase.userDao().pagingSource()
            }
        )
    }
}



