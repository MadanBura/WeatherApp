package com.example.wether_app.retrofit_with_Response_callback.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.retrofit_with_Response_callback.repository.CityRepo

class CityViewModelFactory(private val repository: CityRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}