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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(beers: List<UserEntity>)

    @Query("DELETE FROM User")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM User")
    suspend fun count(): Int


    @Query("SELECT * FROM User")
    fun pagingSource(): PagingSource<Int, UserEntity>
}