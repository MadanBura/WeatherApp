package com.example.wether_app.workManagerDemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM userData_WM")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserData(user: User)

    @Query("SELECT * FROM userData_WM WHERE isSync = 0")
    suspend fun getUnsyncedUsers(): List<User>

    @Query("UPDATE userData_WM SET isSync = 1 WHERE id = :userId")
    suspend fun updateSyncState(userId: Long)

    @Query("DELETE FROM userData_WM WHERE id = :userId")
    suspend fun deleteById(userId: Long)

    @Query("UPDATE userData_WM SET timeStamp = :newTimeStamp WHERE id = :userId")
    suspend fun updateTimeRegistered(userId: Long, newTimeStamp: String)



}