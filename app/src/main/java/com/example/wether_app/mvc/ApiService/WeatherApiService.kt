package com.example.wether_app.mvc.ApiService

import com.example.wether_app.mvc.model.response.WeatherResponse
import retrofit2.Call
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
    ): Call<WeatherResponse>

}