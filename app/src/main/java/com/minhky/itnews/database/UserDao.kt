package com.minhky.itnews.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minhky.itnews.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query(value = "SELECT * FROM user")
    fun getListUser(): Flow<List<UserEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceUser(topicEntities: List<UserEntity>)
}