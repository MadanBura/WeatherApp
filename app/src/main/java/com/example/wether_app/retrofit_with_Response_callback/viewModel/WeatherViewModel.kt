package com.example.wether_app.retrofit_with_Response_callback.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wether_app.retrofit_with_Response_callback.model.response.WeatherResponse_
import com.example.wether_app.retrofit_with_Response_callback.repository.WeatherRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(private val weatherRepo: WeatherRepo) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse_>()
    val weatherData : LiveData<WeatherResponse_> get() = _weatherData

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    fun getWeatherData(apiKey:String, lat_long:String, day : Int){
        weatherRepo.getWeatherData(apiKey, lat_long, day).enqueue(object : Callback<WeatherResponse_>{
            override fun onResponse(
                call: Call<WeatherResponse_>,
                response: Response<WeatherResponse_>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    _weatherData.postValue(response.body())
                }else {
                    _error.postValue("Error: ${response.errorBody()?.string()}")
                }
            }
            override fun onFailure(call: Call<WeatherResponse_>, t: Throwable) {
                _error.postValue("Failure: ${t.message}")
            }
        })
    }

}