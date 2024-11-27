package com.example.wether_app.httpURLConnection.nestedCall.model.response

import com.example.wether_app.mvc.model.Current
import com.example.wether_app.mvc.model.Forecast
import com.example.wether_app.mvc.model.Location

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)