package com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wether_app.BuildConfig
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.ApiResponseClass
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository.ApiRxRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ApiRxViewModel(private val apiRxRepository: ApiRxRepository): ViewModel() {

    private val _apiResponse = MutableLiveData<ApiResponseClass>()
    val apiResponse : LiveData<ApiResponseClass> get() = _apiResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun getApiData(apiKey:String, cityName:String){

        val weatherApiKey = BuildConfig.WEATHER_API_KEY

        apiRxRepository.getCityData(apiKey,cityName)
            .subscribeOn(Schedulers.io())
            .flatMap { cityRes ->
                val latitude = cityRes.firstOrNull()?.latitude ?: 0.0
                val longitude = cityRes.firstOrNull()?.longitude ?: 0.0

                val lat_Long = "${latitude},${longitude}"

                apiRxRepository.getWeatherData(weatherApiKey,lat_Long)
                    .map { weatherResponse ->
                        Pair(cityRes, weatherResponse)
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({combinedResponse ->
                val cityRes = combinedResponse.first
                val weatherRes = combinedResponse.second

                val apiResponseClass = ApiResponseClass(cityRes, weatherRes)
                _apiResponse.value = apiResponseClass

            }, {error ->
                _error.value =error.message
            })

    }





}