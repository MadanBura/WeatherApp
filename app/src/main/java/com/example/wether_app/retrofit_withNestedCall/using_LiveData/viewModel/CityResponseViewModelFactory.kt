package com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.repository.CItyRepo

class CityResponseViewModelFactory(private val cItyRepo: CItyRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityResponseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityResponseViewModel(cItyRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}