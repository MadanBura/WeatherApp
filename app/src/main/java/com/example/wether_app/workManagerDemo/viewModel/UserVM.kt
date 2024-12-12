package com.example.wether_app.workManagerDemo.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wether_app.workManagerDemo.User
import com.example.wether_app.workManagerDemo.model.RegisterResponse
import com.example.wether_app.workManagerDemo.repository.UserRepository
import kotlinx.coroutines.launch


class UserVM(application: Application, private val userRepository: UserRepository) : AndroidViewModel(application) {

    fun register(user: User): LiveData<RegisterResponse> {
        val registrationResponse: MutableLiveData<RegisterResponse> = MutableLiveData()
        viewModelScope.launch {
            try {
                //  Log.d("USERDATA", user.toString())
                userRepository.addUsers(user).collect { response ->
                    //    Log.d("VIEWMODEL RESPONSE", response.toString())
                    if (response != null) {
                        registrationResponse.value = response
                    } else {
                        registrationResponse.value =
                            RegisterResponse(null, "Registration Failed", 400, "")
                    }
                }
            } catch (e: Exception) {
                Log.e("Registration Exception", e.message ?: "Unknown error")
                registrationResponse.value = RegisterResponse(null, "Registration Failed", 400, "")
            }
        }
        return registrationResponse
    }

}