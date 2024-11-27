package com.example.wether_app.retrofit_with_Response_callback.repository

import com.example.wether_app.retrofit_with_Response_callback.apiService.ApiService
import com.example.wether_app.retrofit_with_Response_callback.model.response.WeatherResponse_
import retrofit2.Call


class WeatherRepo(private val weatherService: ApiService) {

    fun getWeatherData(apiKey:String, lat_long:String, day : Int) : Call<WeatherResponse_> {
        return weatherService.getWeatherData(apiKey, lat_long, day)
    }

}