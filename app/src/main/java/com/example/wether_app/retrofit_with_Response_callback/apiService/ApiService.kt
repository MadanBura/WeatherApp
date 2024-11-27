package com.example.wether_app.retrofit_with_Response_callback.apiService

import com.example.wether_app.retrofit_with_Response_callback.model.City
import com.example.wether_app.retrofit_with_Response_callback.model.response.WeatherResponse_
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("city")
    fun fetchDataFromCityApi(
       @Header("X-Api-Key") apiKey : String,
       @Query("name") name : String
    ) : Call<List<City>>

    @GET("forecast.json")
    fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") latlong : String,
        @Query("day1") day1 : Int
    ): Call<WeatherResponse_>

}