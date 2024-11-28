package com.example.wether_app.WebDemo.using_WebView

import com.example.wether_app.WebDemo.using_WebView.modelWebView.CityWebViewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiWebViewService {

    @GET("city")
    suspend fun getCityData(
        @Header("X-Api-Key") key : String,
        @Query("name") cityName : String
    ) : Response<CityWebViewResponse>



}