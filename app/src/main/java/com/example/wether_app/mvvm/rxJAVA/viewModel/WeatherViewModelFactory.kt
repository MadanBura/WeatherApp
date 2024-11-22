package com.example.wether_app.mvvm.rxJAVA.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.mvvm.rxJAVA.repository.WeatherRepo

class WeatherViewModelFactory(
    private val application: Application,
    private val weatherRepo: WeatherRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(application, weatherRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}