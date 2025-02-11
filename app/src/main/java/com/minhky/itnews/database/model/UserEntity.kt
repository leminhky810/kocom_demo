package com.minhky.itnews.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user",
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(defaultValue = "")
    val name : String,
    @ColumnInfo(defaultValue = "")
    val avatarUrl : String,
    @ColumnInfo(defaultValue = "")
    val linkedURL : String
)
