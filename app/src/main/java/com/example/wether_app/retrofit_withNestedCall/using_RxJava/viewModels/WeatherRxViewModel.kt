package com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.WeatherResRxJava
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository.WeatherRxRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherRxViewModel(private val weatherRxRepository: WeatherRxRepository) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResRxJava>()
    val weatherRxData : LiveData<WeatherResRxJava> get() = _weatherData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun getWeatherData(apiKey:String, lat_long:String){
        weatherRxRepository.getWeatherData(apiKey, lat_long)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                _weatherData.value = response
            },{error ->
                _error.value = error.message
            })
    }

}