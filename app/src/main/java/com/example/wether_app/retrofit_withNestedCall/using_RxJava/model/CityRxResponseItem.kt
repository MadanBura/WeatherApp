package com.example.wether_app.retrofit_withNestedCall.using_RxJava.model

data class CityRxResponseItem(
    val country: String,
    val is_capital: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val population: Int,
    val region: String
)