package com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.model.WeatherResponseNestedLiveData
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.repository.WeatherResponseRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherResponseViewModel(private val weatherRepo : WeatherResponseRepo) : ViewModel(){
    fun getWeatherData(apiKey:String, lat_long:String) : LiveData<WeatherResponseNestedLiveData>{
        val weatherResponse : MutableLiveData<WeatherResponseNestedLiveData> = MutableLiveData()

        viewModelScope.launch {
            weatherRepo.getCItyWeatherData(apiKey, lat_long).collect{
                weatherResponse.value = it
            }
        }
        return weatherResponse
    }

}