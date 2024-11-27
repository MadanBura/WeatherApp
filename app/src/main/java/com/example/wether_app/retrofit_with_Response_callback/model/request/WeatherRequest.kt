package com.example.wether_app.retrofit_with_Response_callback.model.request

data class WeatherRequest(
    val key : String,
    val location : String,
    val days : Int,
    val api : String,
    val alerts : String
)