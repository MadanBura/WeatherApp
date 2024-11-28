package com.example.wether_app.WebDemo.using_WebView.viewModelsWebView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wether_app.WebDemo.using_WebView.modelWebView.CityWebViewResponse
import com.example.wether_app.WebDemo.using_WebView.repositoryWebView.CItyWebViewRepo
import kotlinx.coroutines.launch


class CityResWebviewViewModel(private val cityRepo : CItyWebViewRepo) : ViewModel() {

    fun getCityDataFromViewModel(apiKey: String, cityName : String) : LiveData<CityWebViewResponse> {

        val cityResponse : MutableLiveData<CityWebViewResponse> = MutableLiveData()

        viewModelScope.launch {
            cityRepo.getCityData(apiKey, cityName).collect{
                cityResponse.value = it
            }
        }
        return cityResponse
    }


}