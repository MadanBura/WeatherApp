package com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.model.CityWebResponse
import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.repository.CItyWebRepo
import kotlinx.coroutines.launch


class CityResWebViewModel(private val cityRepo : CItyWebRepo) : ViewModel() {

    fun getCityDataFromViewModel(apiKey: String, cityName : String) : LiveData<CityWebResponse> {

        val cityResponse : MutableLiveData<CityWebResponse> = MutableLiveData()

        viewModelScope.launch {
            cityRepo.getCityData(apiKey, cityName).collect{
                cityResponse.value = it
            }
        }
        return cityResponse
    }


}