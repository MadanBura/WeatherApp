package com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel.CityResponseViewModel
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository.CityRxRepository

class CityRxViewModelFactory(private val cityRxRepository: CityRxRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityRxViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityRxViewModel(cityRxRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}