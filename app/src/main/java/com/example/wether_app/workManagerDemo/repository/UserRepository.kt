package com.example.wether_app.workManagerDemo.repository

import android.util.Log
import com.example.wether_app.workManagerDemo.User
import com.example.wether_app.workManagerDemo.utils.RetrofitInstance
import kotlinx.coroutines.flow.flow


class UserRepository {
    val apiService = RetrofitInstance.userApiService
    fun addUsers(user: User) = flow {
        try {
            val response = apiService.registerUser(
                user.first_name,
                user.last_name,
                user.email,
                user.password,
                user.confirm_password,
                user.gender,
                user.phone_no.toString()
            )
            if (response.isSuccessful && response.body() != null) {
                emit(response.body())
            } else {
                Log.e(
                    "API Error",
                    "Error Code: ${response.code()}, Error Message: ${response.message()}"
                )
                emit(null)
            }
        } catch (e: Exception) {
            Log.d("REPO RESPONSE", "Failed due to : ${e.message}")
        }

    }
}