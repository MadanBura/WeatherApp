package com.example.wether_app.httpURLConnection.nestedCall.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wether_app.httpURLConnection.nestedCall.LatLongClass
import com.example.wether_app.httpURLConnection.nestedCall.NetworkNestedHelper
import kotlinx.coroutines.launch
import org.json.JSONArray

class WeatherNestedViewModel : ViewModel() {

    private val _getResponse = MutableLiveData<LatLongClass>()
    val getResponse: LiveData<LatLongClass> get() = _getResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    fun fetchCityData(city : String) {
        viewModelScope.launch {
            try {
                val response = NetworkNestedHelper.fetchData(city)
                Log.d("API Response", response)

                val jsonArray = JSONArray(response)

                if (jsonArray.length() > 0) {
                    val cityData = jsonArray.getJSONObject(0)

                    val lat = cityData.getDouble("latitude")
                    val long = cityData.getDouble("longitude")

                    val latLongObj = LatLongClass(lat_ = lat, long_ = long)

                    _getResponse.postValue(latLongObj)
                }else {
                    _errorMessage.postValue("City_nestedCall not found")
                }
            } catch (e: Exception) {
                Log.e("fetchCityData", "Error fetching or parsing city data", e)
                _errorMessage.postValue("Error fetching city data: ${e.message}")
            }
        }
    }


    private val _getRes = MutableLiveData<String>()
    val getR: LiveData<String> get() = _getRes

    fun fetchWeatherData(lat_ : Double, long_ :Double) {
        viewModelScope.launch {
            val response = NetworkNestedHelper.fetchWeatherData(lat_, long_)

            _getRes.postValue(response)
        }
    }

}