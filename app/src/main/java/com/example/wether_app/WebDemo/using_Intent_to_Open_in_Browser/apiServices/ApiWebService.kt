package com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.apiServices

import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.model.CityWebResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiWebService {

    @GET("city")
    suspend fun getCityData(
        @Header("X-Api-Key") key : String,
        @Query("name") cityName : String
    ) : Response<CityWebResponse>



}