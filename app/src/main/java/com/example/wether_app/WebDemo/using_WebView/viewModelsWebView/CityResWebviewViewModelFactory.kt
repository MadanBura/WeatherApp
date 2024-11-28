package com.example.wether_app.WebDemo.using_WebView.viewModelsWebView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wether_app.WebDemo.using_WebView.repositoryWebView.CItyWebViewRepo

class CityResWebviewViewModelFactory(private val cItyRepo: CItyWebViewRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityResWebviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityResWebviewViewModel(cItyRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}