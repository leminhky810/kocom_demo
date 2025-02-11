package com.minhky.itnews.data.model

import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.network.model.UserResponse

fun UserResponse.toUserEntity(): UserEntity = UserEntity(
    name = login ?: "",
    avatarUrl = avatarUrl ?: "",
    linkedURL = htmlUrl ?: ""
)