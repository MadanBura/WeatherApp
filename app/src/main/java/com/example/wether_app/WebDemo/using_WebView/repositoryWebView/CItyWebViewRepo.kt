package com.example.wether_app.WebDemo.using_WebView.repositoryWebView

import com.example.wether_app.WebDemo.using_WebView.viewModelsWebView.RetrofitInstanceWebView
import kotlinx.coroutines.flow.flow


class CItyWebViewRepo{

    private  val cityApiService = RetrofitInstanceWebView.cityWebService
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