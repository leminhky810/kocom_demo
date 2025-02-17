package com.minhky.itnews.data


import com.minhky.itnews.core.AppDispatcher
import com.minhky.itnews.core.Dispatcher
import com.minhky.itnews.data.model.toUserEntity
import com.minhky.itnews.database.UserDao
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.database.model.toUser
import com.minhky.itnews.model.User
import com.minhky.itnews.network.UserNetworkDataSource
import com.minhky.itnews.network.model.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstUserRepository @Inject constructor(
    private val userNetworkDataSource: UserNetworkDataSource,
    private val userDao: UserDao,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : ListUserRepository {

    override suspend fun fetchUser(numberOfUser: Int, since: Int): Flow<List<User>> {
         return  withContext(ioDispatcher){
            userDao.getListUser().onEach { userList ->
                if (userList.isEmpty()){
                    userNetworkDataSource.getUsers(30, 0).map {
                        it.toUserEntity()
                    }.also {
                        userDao.insertOrReplaceUser(it)
                    }
                }
            }.map { listUser ->
                listUser.map { it.toUser() }
            }
        }
    }
}