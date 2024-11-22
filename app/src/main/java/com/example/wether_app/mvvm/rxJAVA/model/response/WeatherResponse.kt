package com.example.wether_app.mvvm.rxJAVA.model.response

import com.example.wether_app.mvvm.rxJAVA.model.Current
import com.example.wether_app.mvvm.rxJAVA.model.Forecast
import com.example.wether_app.mvvm.rxJAVA.model.Location

data class WeatherResponse(
    val current: Current?,
    val forecast: Forecast?,
    val location: Location?
)