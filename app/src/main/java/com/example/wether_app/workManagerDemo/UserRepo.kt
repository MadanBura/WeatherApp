package com.example.wether_app.workManagerDemo

import androidx.lifecycle.LiveData

class UserRepo(
    private val userDao: UserDao
) {

    suspend fun updateTimeRegistered(userId: Long, timeStamp:String) = userDao.updateTimeRegistered(userId, timeStamp)

    suspend fun deleteUserById(userId: Long) = userDao.deleteById(userId)

    suspend fun getUnsyncedUsers(): List<User> = userDao.getUnsyncedUsers()

    suspend fun updateSyncState(userId: Long) = userDao.updateSyncState(userId)

    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    suspend fun addUserIntoDb(user: User) {
        userDao.addUserData(user)
    }
}