package com.example.wether_app.mvvm.liveData.repositoryMVVM

import android.util.Log
import com.example.wether_app.mvvm.liveData.model.request.WeatherRequest
import com.example.wether_app.mvvm.liveData.utilsMVVM.RetrofitInstance
import kotlinx.coroutines.flow.flow

class WeatherRepo_MVVM {

    val weatherApiService = RetrofitInstance.weatherService

     fun getWeatherData(weatherRequest : WeatherRequest) = flow {
         try {
             val weatherResponse = weatherApiService.fetchWeatherData(
                 weatherRequest.key,
                 weatherRequest.location,
                 weatherRequest.days,
                 weatherRequest.api,
                 weatherRequest.alerts
             )
             if (weatherResponse.isSuccessful && weatherResponse != null){
                 emit(weatherResponse.body())
             }else{
                 Log.e(
                     "API Error",
                     "Error Code: ${weatherResponse.code()}, Error Message: ${weatherResponse.message()}"
                 )
                 emit(null)
             }

         }catch (e : Exception){
             Log.d("REPO RESPONSE", "Failed due to : ${e.message}")
         }
     }

}