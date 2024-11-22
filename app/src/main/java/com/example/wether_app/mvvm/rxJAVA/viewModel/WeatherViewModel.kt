package com.example.wether_app.mvvm.rxJAVA.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wether_app.mvvm.rxJAVA.model.request.WeatherRequest
import com.example.wether_app.mvvm.rxJAVA.model.response.WeatherResponse
import com.example.wether_app.mvvm.rxJAVA.repository.WeatherRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherViewModel(
    application: Application, private val weatherrepo: WeatherRepo
) : AndroidViewModel(application) {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> = _weatherData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchWeatherData(key: String, location: String, days: Int, aqi: String, alerts: String) {
        weatherrepo.fetchWeatherData(key, location, days, aqi, alerts)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _weatherData.value = response
            }, { throwable ->
                _error.value = throwable.message
            })
    }
}