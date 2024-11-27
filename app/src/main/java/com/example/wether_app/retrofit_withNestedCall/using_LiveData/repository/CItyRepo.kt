package com.example.wether_app.retrofit_withNestedCall.using_LiveData.repository

import android.util.Log
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.utils.RetrofitResponseInstance
import kotlinx.coroutines.flow.flow

class CItyRepo {

    private  val cityApiService = RetrofitResponseInstance.cityService
    fun getCityData(apiKey:String, cityName : String) = flow{
        try {
            val cityResponse = cityApiService.getCityData(key = apiKey, cityName = cityName)

            Log.e("CityReponse_repo", cityResponse.toString())
            if(cityResponse!=null){
                emit(cityResponse.body())
            }

        }catch (e : Exception){
            e.printStackTrace()
        }
    }

}