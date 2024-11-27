package com.example.wether_app.retrofit_with_Response_callback.repository

import com.example.wether_app.retrofit_with_Response_callback.apiService.ApiService
import com.example.wether_app.retrofit_with_Response_callback.model.City
import retrofit2.Call


class CityRepo(private val cityApiService: ApiService){

    fun fetchCityData(apiKey:String, cityName:String) : Call<List<City>>{
        return cityApiService.fetchDataFromCityApi(
            apiKey = apiKey,
            name = cityName
        )
    }

}