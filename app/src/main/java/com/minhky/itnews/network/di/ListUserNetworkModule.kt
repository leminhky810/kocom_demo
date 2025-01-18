package com.minhky.itnews.network.di

import com.minhky.itnews.network.KtorNetworkDataSource
import com.minhky.itnews.network.UserNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ListUserNetworkModule {
    @Binds
    fun binds(impl: KtorNetworkDataSource): UserNetworkDataSource
}