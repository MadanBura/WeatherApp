package com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.repository.CItyWebRepo

class CityResWebViewModelFactory(private val cItyRepo: CItyWebRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityResWebViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityResWebViewModel(cItyRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}