package com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.utils

import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.apiServices.ApiWebService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstanceWeb {

    private val intercepter = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(intercepter)
        .build()

    fun getRetrofitInstance() : Retrofit {
       return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val cityWebService : ApiWebService by lazy {
        getRetrofitInstance().create(ApiWebService::class.java)
    }

}
