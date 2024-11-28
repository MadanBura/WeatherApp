package com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository

import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.WeatherResRxJava
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.utils.RetrofitRxInstance
import io.reactivex.rxjava3.core.Observable

class WeatherRxRepository {

    val weatherRxServices = RetrofitRxInstance.weatherRxService

    fun getWeatherData(apikey:String, lat_long:String) :Observable<WeatherResRxJava>{
        return weatherRxServices.fetchWeatherData(apikey, lat_long)
    }

}