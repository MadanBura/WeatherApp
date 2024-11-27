package com.example.wether_app.volley.volley_withGson.model.request

data class WeatherRequest(
    val key : String,
    val location : String,
    val days : Int,
    val api : String,
    val alerts : String
)