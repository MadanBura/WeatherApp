package com.example.wether_app.retrofit_with_Response_callback.viewModel

import com.example.wether_app.retrofit_with_Response_callback.repository.CityRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wether_app.retrofit_with_Response_callback.model.City
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityViewModel(private val cityRepo: CityRepo) : ViewModel(){

    private val _cityData = MutableLiveData<List<City>>()
    val cityData: LiveData<List<City>> get() = _cityData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getCityData(apiKey: String, cityName: String) {
        cityRepo.fetchCityData(apiKey, cityName).enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.isSuccessful) {
                    _cityData.postValue(response.body())
                } else {
                    _error.postValue("Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                _error.postValue("Failure: ${t.message}")
            }
        })
    }

}