package com.example.wether_app.mvp.repository

import com.example.wether_app.mvp.apiService.WeatherApiService_MVP
import com.example.wether_app.mvp.model.response.WeatherResponse
import retrofit2.Call

class WeatherRepoMVP(private val weatherService: WeatherApiService_MVP) {

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