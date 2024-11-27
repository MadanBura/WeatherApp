package com.example.wether_app.httpURLConnection.using_MVVM.Model.request

data class WeatherRequest(
    val key : String,
    val location : String,
    val days : Int,
    val api : String,
    val alerts : String
)