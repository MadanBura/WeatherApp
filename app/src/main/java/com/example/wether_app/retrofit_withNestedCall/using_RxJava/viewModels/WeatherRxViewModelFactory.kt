package com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository.WeatherRxRepository

class WeatherRxViewModelFactory(private val weatherRxRepository: WeatherRxRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherRxViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherRxViewModel(weatherRxRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}