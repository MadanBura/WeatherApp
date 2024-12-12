package com.example.wether_app.workManagerDemo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.workManagerDemo.repository.UserRepository

class UserVMFactory (  private val application: Application,
private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserVM(application, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}