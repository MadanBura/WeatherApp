package com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.model.CityRxResponse
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository.CityRxRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CityRxViewModel(private val cityRxRepository: CityRxRepository) : ViewModel() {

    private val _cityData = MutableLiveData<CityRxResponse>()
    val cityData : LiveData<CityRxResponse> get() = _cityData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCityData(apiKey:String, cityName:String) {
        cityRxRepository.getCityData(apiKey, cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                _cityData.value = response
            },{error ->
                _error.value =error.message
            })
    }

}