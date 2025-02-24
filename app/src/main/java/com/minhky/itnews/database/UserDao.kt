package com.minhky.itnews.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.minhky.itnews.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getUser(): List<UserEntity>

    @Upsert
    suspend fun upsertAll(beers: List<UserEntity>)

    @Query("DELETE FROM user WHERE name = :query")
    suspend fun deleteByQuery(query: String)

    @Query("SELECT * FROM User")
    fun pagingSource(): PagingSource<Int, UserEntity>
}