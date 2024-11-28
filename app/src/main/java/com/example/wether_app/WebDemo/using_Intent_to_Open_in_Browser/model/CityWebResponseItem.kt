package com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.model

data class CityWebResponseItem(
    val country: String,
    val is_capital: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val population: Int,
    val region: String
)