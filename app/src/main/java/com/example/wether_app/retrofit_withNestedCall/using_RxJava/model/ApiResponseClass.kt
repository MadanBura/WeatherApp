package com.example.wether_app.retrofit_withNestedCall.using_RxJava.model

data class ApiResponseClass(
    val cityRxResponse: CityRxResponse,
    val weatherResRxJava: WeatherResRxJava
)