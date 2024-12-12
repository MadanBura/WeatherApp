package com.example.wether_app.workManagerDemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserViewModel(
    private val userRepo: UserRepo
) : ViewModel(){

    val getAllUsers = userRepo.getAllUsers()

    suspend fun addUserIntoDb(user: User): Boolean {
       return try {
            userRepo.addUserIntoDb(user)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteFromDb(userId : Long) : Boolean{
        return try {
            userRepo.deleteUserById(userId)
            true
        } catch (e: Exception) {
            false
        }
    }

}