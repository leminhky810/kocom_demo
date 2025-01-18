package com.minhky.itnews.data.di

import com.minhky.itnews.data.ListUserRepository
import com.minhky.itnews.data.OfflineFirstUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindListUserRepository(
        listUserRepository: OfflineFirstUserRepository,
    ): ListUserRepository

}
