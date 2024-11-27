package com.example.wether_app.retrofit_withNestedCall.using_LiveData.utils

import com.example.wether_app.retrofit_withNestedCall.using_LiveData.apiService.ApiResponseServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitResponseInstance {

    private const val BASE_CITY_URI = "https://api.api-ninjas.com/v1/"
    private const val BASE_WEATHER_URI = "https://api.weatherapi.com/v1/"

    private val intercepter = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(intercepter)
        .build()

    private fun getRetrofitInstance(baseUri : String) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUri)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val cityService: ApiResponseServices by lazy {
        getRetrofitInstance(BASE_CITY_URI).create(ApiResponseServices::class.java)
    }

    val weatherService: ApiResponseServices by lazy {
        getRetrofitInstance(BASE_WEATHER_URI).create(ApiResponseServices::class.java)
    }

}