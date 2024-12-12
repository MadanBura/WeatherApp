package com.example.wether_app.workManagerDemo

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.wether_app.workManagerDemo.model.RegisterResponse
import com.example.wether_app.workManagerDemo.utils.RetrofitInstance
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date

class UserSyncWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val userDao: UserDao by lazy {
        RoomDbHelper.getDatabase(context).userDao()
    }

    override suspend fun doWork(): Result {
        val unsyncedUsers = userDao.getUnsyncedUsers()

        unsyncedUsers.forEach { user ->
            try {
                val apiResponse = syncUserWithServer(user)

                if (apiResponse.isSuccessful) {
                    val currentTime = System.currentTimeMillis()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val registeredTimeStamp = dateFormat.format(Date(currentTime))

                    userDao.updateTimeRegistered(userId = user.id, registeredTimeStamp.toString())
                    userDao.updateSyncState(user.id)
                } else {
                    return Result.retry()
                }
            } catch (e: Exception) {
                // Handle exceptions
                return Result.retry()
            }
        }
        return Result.success()
    }

    private suspend fun syncUserWithServer(user: User): Response<RegisterResponse> {
        val apiService = RetrofitInstance.userApiService
        val response = apiService.registerUser(
            user.first_name,
            user.last_name,
            user.email,
            user.password,
            user.confirm_password,
            user.gender,
            user.phone_no.toString()
        )

       return response
    }
}
