package com.minhky.itnews.database.di

import com.minhky.itnews.database.ITNewsDataBase
import com.minhky.itnews.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun provideUserDao(
        database: ITNewsDataBase,
    ): UserDao = database.userDao()

}