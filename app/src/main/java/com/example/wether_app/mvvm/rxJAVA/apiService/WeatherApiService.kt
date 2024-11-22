package com.example.wether_app.mvvm.rxJAVA.apiService

import com.example.wether_app.mvvm.rxJAVA.model.response.WeatherResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

      @GET("forecast.json")
      fun fetchWeatherData(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Observable<WeatherResponse>

}