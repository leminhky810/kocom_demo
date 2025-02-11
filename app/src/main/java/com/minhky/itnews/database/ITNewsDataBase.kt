package com.minhky.itnews.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.minhky.itnews.database.model.UserEntity


@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = true,
)


internal abstract class ITNewsDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}