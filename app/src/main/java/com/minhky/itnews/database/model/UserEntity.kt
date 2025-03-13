package com.minhky.itnews.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.minhky.itnews.model.User

@Entity(
    tableName = "user",
)
data class UserEntity(
    @PrimaryKey
    val id: Int ,
    @ColumnInfo(defaultValue = "")
    val name: String,
    @ColumnInfo(defaultValue = "")
    val avatarUrl: String,
    @ColumnInfo(defaultValue = "")
    val linkedURL: String
)

fun UserEntity.toUser(): User = User(
    name = name,
    avatarUrl = avatarUrl,
    linkedURL = linkedURL
)