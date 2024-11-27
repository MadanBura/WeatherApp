package com.example.wether_app.retrofit_withNestedCall.using_LiveData.model

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)