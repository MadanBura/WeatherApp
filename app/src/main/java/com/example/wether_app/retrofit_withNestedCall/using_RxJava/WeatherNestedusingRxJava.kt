package com.example.wether_app.retrofit_withNestedCall.using_RxJava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.wether_app.BuildConfig
import com.example.wether_app.databinding.ActivityWeatherNestedUsingRxJavaBinding
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository.CityRxRepository
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels.CityRxViewModel
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels.CityRxViewModelFactory
import android.view.View
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.repository.WeatherRxRepository
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels.WeatherRxViewModel
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.viewModels.WeatherRxViewModelFactory

class WeatherNestedusingRxJava : AppCompatActivity() {

    private lateinit var binding : ActivityWeatherNestedUsingRxJavaBinding

    private val cityRxViewModel : CityRxViewModel by viewModels {
        CityRxViewModelFactory(CityRxRepository())
    }

    private val weatherRxViewModel : WeatherRxViewModel by viewModels {
        WeatherRxViewModelFactory(WeatherRxRepository())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherNestedUsingRxJavaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val city_API_KEY = BuildConfig.CITY_API_KEY
        val weather_API_KEY =  BuildConfig.WEATHER_API_KEY



        binding.btnSearch.setOnClickListener {

            val cityName = binding.etSearchLocation.text.toString()

            cityRxViewModel.getCityData(city_API_KEY, cityName)
            cityRxViewModel.cityData.observe(this, Observer { cityResponse ->
                val lat = cityResponse[0].latitude.toString()
                val long = cityResponse[0].longitude.toString()

                binding.etLat.text = lat
                binding.etLong.text= long

                val lat_long = "$lat,$long"

                binding.btnGetWeatherData.visibility = View.VISIBLE
                binding.btnGetWeatherData.setOnClickListener {

                    weatherRxViewModel.getWeatherData(weather_API_KEY, lat_long)
                    weatherRxViewModel.weatherRxData.observe(this,Observer{
                        binding.tvTemp.text = it.toString()
                    })
                }
            })
        }
    }
}