package com.example.wether_app.retrofit_withNestedCall.using_RxJava.apiRxServices

import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.CityRxResponse
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.WeatherResRxJava
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiRxServices {


    @GET("city")
    fun getCityDataFromApi(
        @Header("X-Api-Key") key : String,
        @Query("name") name : String
    ) : Observable<CityRxResponse>

    @GET("forecast.json")
    fun fetchWeatherData(
        @Query("key") key: String,
        @Query("q") lat_long: String,
        @Query("days") days: Int = 1,
    ): Observable<WeatherResRxJava>


}