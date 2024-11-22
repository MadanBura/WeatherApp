package com.example.wether_app.mvvm.liveData.apiService_MVVM

import com.example.wether_app.mvvm.liveData.model.response.WeatherResponse_MVVM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

      @GET("forecast.json")
      suspend fun fetchWeatherData(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Response<WeatherResponse_MVVM>

}