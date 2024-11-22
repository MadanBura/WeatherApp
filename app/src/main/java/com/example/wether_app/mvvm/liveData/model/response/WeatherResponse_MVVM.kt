package com.example.wether_app.mvvm.liveData.model.response

import com.example.wether_app.mvvm.liveData.model.Current
import com.example.wether_app.mvvm.liveData.model.Forecast
import com.example.wether_app.mvvm.liveData.model.Location


data class WeatherResponse_MVVM(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)