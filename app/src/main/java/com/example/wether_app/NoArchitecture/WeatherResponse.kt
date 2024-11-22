package com.example.wether_app.NoArchitecture

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)