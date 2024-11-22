package com.example.wether_app.mvvm.liveData.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.mvvm.liveData.repositoryMVVM.WeatherRepo_MVVM

class WeatherViewModelFactory(
    private val application: Application,
    private val weatherRepo: WeatherRepo_MVVM
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(application, weatherRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
