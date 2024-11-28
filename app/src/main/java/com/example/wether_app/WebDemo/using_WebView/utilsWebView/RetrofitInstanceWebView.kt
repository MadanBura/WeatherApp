package com.example.wether_app.WebDemo.using_WebView.viewModelsWebView

import com.example.wether_app.WebDemo.using_WebView.ApiWebViewService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceWebView {

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

    val cityWebService : ApiWebViewService by lazy {
        getRetrofitInstance().create(ApiWebViewService::class.java)
    }

}
