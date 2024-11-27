package com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.repository.WeatherResponseRepo

class WeatherResponseViewModelFactory(
    private val weatherRepo : WeatherResponseRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherResponseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherResponseViewModel(weatherRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
