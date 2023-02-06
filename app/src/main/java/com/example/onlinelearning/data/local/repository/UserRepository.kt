package com.example.onlinelearning.data.local.repository

import com.example.onlinelearning.data.local.dao.UsersDao
import com.example.onlinelearning.data.local.entity.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val usersDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun addUser(userEntity: UserEntity) = withContext(ioDispatcher) {
        usersDao.addUser(userEntity) != NO_USER_ADDED_FLAG
    }

    suspend fun getUser(username: String) = withContext(ioDispatcher) {
        usersDao.getUser(username)
    }

    suspend fun getUserId(username: String) = withContext(ioDispatcher) {
        usersDao.getUser(username)?.id
    }

    suspend fun isUserExists(username: String) = withContext(ioDispatcher) {
        usersDao.getUser(username) != null
    }

    companion object {
        private const val NO_USER_ADDED_FLAG = -1L
    }
}