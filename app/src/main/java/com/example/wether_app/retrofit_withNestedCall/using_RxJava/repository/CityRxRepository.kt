package com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository

import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.CityRxResponse
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.utils.RetrofitRxInstance
import io.reactivex.rxjava3.core.Observable

class CityRxRepository {

    val cityRxService = RetrofitRxInstance.cityRxService

    fun getCityData(apiKey:String, cityName:String) : Observable<CityRxResponse>{
        return cityRxService.getCityDataFromApi(apiKey, cityName)
    }

}