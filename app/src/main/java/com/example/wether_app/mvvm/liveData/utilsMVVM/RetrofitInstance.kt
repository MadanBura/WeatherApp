package com.example.wether_app.mvvm.liveData.utilsMVVM

import com.example.wether_app.mvvm.liveData.apiService_MVVM.WeatherApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val intercepter = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(intercepter)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val weatherService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}
