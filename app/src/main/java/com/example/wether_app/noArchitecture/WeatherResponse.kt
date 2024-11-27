package com.example.wether_app.noArchitecture

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)