package com.example.wether_app.retrofit_with_Response_callback.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.retrofit_with_Response_callback.repository.CityRepo
import com.example.wether_app.retrofit_with_Response_callback.repository.WeatherRepo

class WeatherViewModelFactory(private val repository: WeatherRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}