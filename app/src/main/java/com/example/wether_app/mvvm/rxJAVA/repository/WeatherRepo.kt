package com.example.wether_app.mvvm.rxJAVA.repository

import com.example.wether_app.mvvm.rxJAVA.model.response.WeatherResponse
import com.example.wether_app.mvvm.rxJAVA.utils.RetrofitInstance
import io.reactivex.rxjava3.core.Observable

class WeatherRepo {

    val weatherApiService = RetrofitInstance.weatherService

    fun fetchWeatherData(key: String, location: String, days: Int, aqi: String, alerts: String): Observable<WeatherResponse> {
        return weatherApiService.fetchWeatherData(key, location, days, aqi, alerts)
    }

}