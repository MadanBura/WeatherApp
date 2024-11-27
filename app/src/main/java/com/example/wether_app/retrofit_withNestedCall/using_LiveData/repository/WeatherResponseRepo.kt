package com.example.wether_app.retrofit_withNestedCall.using_LiveData.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.model.WeatherResponseNestedLiveData
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.utils.RetrofitResponseInstance
import kotlinx.coroutines.flow.flow

class WeatherResponseRepo {

    private val weatherApiService  =RetrofitResponseInstance.weatherService
    fun getCItyWeatherData(apikey:String, lat_long:String) = flow {

        try {
            val weatherResponse = weatherApiService.getCityWeatherData(apikey, lat_long)
            if(weatherResponse.isSuccessful){
                emit(weatherResponse.body())
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}