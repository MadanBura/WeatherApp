package com.example.wether_app.mvc.repository

import com.example.wether_app.mvc.ApiService.WeatherApiService
import com.example.wether_app.mvc.model.response.WeatherResponse
import retrofit2.Call

class WeatherRepo(private val weatherService: WeatherApiService) {

    fun fetchWeatherData(
        apiKey: String,
        location: String,
        days: Int,
        aqi: String,
        alerts: String
    ): Call<WeatherResponse> {
        return weatherService.fetchWeatherData(apiKey, location, days, aqi, alerts)
    }
}