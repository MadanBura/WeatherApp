package com.example.wether_app.httpURLConnection.nestedCall.model

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)