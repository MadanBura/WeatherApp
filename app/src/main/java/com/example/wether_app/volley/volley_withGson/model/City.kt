package com.example.wether_app.volley.volley_withGson.model

data class City(
    val country: String,
    val is_capital: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val population: Int,
    val region: String
)