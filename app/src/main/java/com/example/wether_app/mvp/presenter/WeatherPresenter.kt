package com.example.wether_app.mvp.presenter


import com.example.wether_app.mvp.model.response.WeatherResponse
import com.example.wether_app.mvp.repository.WeatherRepoMVP
import com.example.wether_app.mvp.view.WeatherViewContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPresenter(
    private val view: WeatherViewContract.View,
    private val weatherRepo: WeatherRepoMVP
) : WeatherViewContract.Presenter {

    override fun fetchWeatherData() {

        val key = "55ae7587d2194b30bf364918242111"
        val location = "Kalyan"
        val days = 1
        val aqi = "no"
        val alerts = "no"

        // Asynchronous request call
        val call: Call<WeatherResponse> = weatherRepo.fetchWeatherData(key, location, days, aqi, alerts)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { weatherResponse ->
                        view.showWeatherData(weatherResponse)
                    }
                } else {
                    view.showError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                view.showError("Failure: ${t.message}")
            }
        })
    }
}
