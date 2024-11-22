package com.example.wether_app.mvp.view

import com.example.wether_app.mvp.model.response.WeatherResponse

interface WeatherViewContract {

    interface View {
        fun showWeatherData(weatherResponse: WeatherResponse)
        fun showError(message: String)
    }

    interface Presenter {
        fun fetchWeatherData()
    }

}