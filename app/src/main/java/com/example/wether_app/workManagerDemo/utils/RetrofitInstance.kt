package com.example.wether_app.workManagerDemo.utils

import com.example.wether_app.mvvm.rxJAVA.apiService.WeatherApiService
import com.example.wether_app.workManagerDemo.apiServices.UserApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val intercepter = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(intercepter)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://staging.php-dev.in:8844/trainingapp/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()

    val userApiService: UserApiServices by lazy {
        retrofit.create(UserApiServices::class.java)
    }


}