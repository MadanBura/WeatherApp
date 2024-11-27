package com.example.wether_app.httpURLConnection.using_MVVM.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wether_app.httpURLConnection.using_MVVM.util.NetworkHelper
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _getResponse = MutableLiveData<String>()
    val getResponse: LiveData<String> get() = _getResponse

    fun fetchGetData() {
       viewModelScope.launch {
           val response = NetworkHelper.fetchData()

           _getResponse.postValue(response)
       }
    }

}