package com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.model.CityRes
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.repository.CItyRepo
import kotlinx.coroutines.launch


class CityResponseViewModel(private val cityRepo : CItyRepo) : ViewModel() {

    fun getCityDataFromViewModel(apiKey: String, cityName : String) : LiveData<CityRes>{

        val cityResponse : MutableLiveData<CityRes> = MutableLiveData()

        viewModelScope.launch {
            cityRepo.getCityData(apiKey, cityName).collect{
                cityResponse.value = it
            }
        }
        return cityResponse
    }


}