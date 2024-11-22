package com.example.wether_app.mvvm.liveData.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wether_app.mvvm.liveData.model.request.WeatherRequest
import com.example.wether_app.mvvm.liveData.repositoryMVVM.WeatherRepo_MVVM
import com.example.wether_app.mvvm.liveData.model.response.WeatherResponse_MVVM
import kotlinx.coroutines.launch

class WeatherViewModel(
    application: Application, private val weatherrepoMvvm: WeatherRepo_MVVM
) : AndroidViewModel(application) {


    fun getWeatherData(weatherRequest: WeatherRequest): LiveData<WeatherResponse_MVVM> {
        val weatherResponse: MutableLiveData<WeatherResponse_MVVM> = MutableLiveData()

        viewModelScope.launch {
            try {
                weatherrepoMvvm.getWeatherData(weatherRequest).collect {
                    weatherResponse.value = it
                }
            }catch (e : Exception){
                Log.e("Data loadng Exception", e.message ?: "Unknown error")

            }
        }
            return weatherResponse
    }
}