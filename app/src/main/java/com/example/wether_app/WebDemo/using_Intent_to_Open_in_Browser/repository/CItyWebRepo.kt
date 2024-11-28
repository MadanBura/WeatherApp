package com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.repository

import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.utils.RetrofitInstanceWeb
import kotlinx.coroutines.flow.flow


class CItyWebRepo{

    private  val cityApiService = RetrofitInstanceWeb.cityWebService
    fun getCityData(apiKey:String, cityName : String) = flow{
        try {
            val cityResponse = cityApiService.getCityData(key = apiKey, cityName = cityName)

            if(cityResponse!=null){
                emit(cityResponse.body())
            }

        }catch (e : Exception){
            e.printStackTrace()
        }
    }

}