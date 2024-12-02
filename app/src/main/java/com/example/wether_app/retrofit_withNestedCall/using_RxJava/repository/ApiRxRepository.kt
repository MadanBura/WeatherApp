package com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository

import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.CityRxResponse
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.WeatherResRxJava
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.utils.RetrofitRxInstance
import io.reactivex.rxjava3.core.Observable

class ApiRxRepository {
    val cityRxService = RetrofitRxInstance.cityRxService
    val weatherRxServices = RetrofitRxInstance.weatherRxService

    fun getCityData(apiKey:String, cityName:String) : Observable<CityRxResponse>{
        return cityRxService.getCityDataFromApi(apiKey, cityName)
    }

    fun getWeatherData(apikey:String, lat_long:String) :Observable<WeatherResRxJava>{
        return weatherRxServices.fetchWeatherData(apikey, lat_long)
    }

}