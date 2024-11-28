package com.example.wether_app.retrofit_withNestedCall.using_RxJava.utils

import com.example.wether_app.retrofit_withNestedCall.using_RxJava.apiRxServices.ApiRxServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRxInstance {


    private const val BASE_CITY_URI = "https://api.api-ninjas.com/v1/"
    private const val BASE_WEATHER_URI = "https://api.weatherapi.com/v1/"

    private val intercepter = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(intercepter)
        .build()

    private fun getRetrofitInstance(baseUri : String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUri)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }

    val cityRxService: ApiRxServices by lazy {
        getRetrofitInstance(BASE_CITY_URI).create(ApiRxServices::class.java)
    }

    val weatherRxService: ApiRxServices by lazy {
        getRetrofitInstance(BASE_WEATHER_URI).create(ApiRxServices::class.java)
    }


}