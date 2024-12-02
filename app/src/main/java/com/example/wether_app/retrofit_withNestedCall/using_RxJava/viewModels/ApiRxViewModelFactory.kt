package com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository.ApiRxRepository

class ApiRxViewModelFactory(private val apiRxRepository: ApiRxRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiRxViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApiRxViewModel(apiRxRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}