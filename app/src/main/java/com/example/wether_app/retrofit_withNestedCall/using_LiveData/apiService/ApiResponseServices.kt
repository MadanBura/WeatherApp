package com.example.wether_app.retrofit_withNestedCall.using_LiveData.apiService

import com.example.wether_app.retrofit_withNestedCall.using_LiveData.model.CityRes
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.model.WeatherResponseNestedLiveData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiResponseServices {

    @GET("city")
    suspend fun getCityData(
        @Header("X-Api-Key") key : String,
        @Query("name") cityName : String
    ) : Response<CityRes>


    @GET("forecast.json")
    suspend fun getCityWeatherData(
        @Query("key") key: String,
        @Query("q") lat_lng : String,
        @Query("day") day :Int = 1
    ) : Response<WeatherResponseNestedLiveData>


}