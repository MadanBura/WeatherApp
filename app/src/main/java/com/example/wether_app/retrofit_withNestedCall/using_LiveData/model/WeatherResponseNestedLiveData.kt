package com.example.wether_app.retrofit_withNestedCall.using_LiveData.model

data class WeatherResponseNestedLiveData(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)