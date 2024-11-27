package com.example.wether_app.retrofit_with_Response_callback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.wether_app.BuildConfig
import com.example.wether_app.databinding.ActivityWeatherAppUsingResponseCallbackBinding
import com.example.wether_app.retrofit_with_Response_callback.repository.CityRepo
import com.example.wether_app.retrofit_with_Response_callback.util.RetrofitInstance
import com.example.wether_app.retrofit_with_Response_callback.viewModel.CityViewModel
import com.example.wether_app.retrofit_with_Response_callback.viewModel.CityViewModelFactory
import android.view.View
import com.example.wether_app.retrofit_with_Response_callback.repository.WeatherRepo
import com.example.wether_app.retrofit_with_Response_callback.viewModel.WeatherViewModel
import com.example.wether_app.retrofit_with_Response_callback.viewModel.WeatherViewModelFactory

class WeatherApp_using_response_callback : AppCompatActivity() {

    private lateinit var binding : ActivityWeatherAppUsingResponseCallbackBinding

    private val cityViewModel: CityViewModel by viewModels {
        CityViewModelFactory(CityRepo(RetrofitInstance.cityService))
    }


    private val weatherViewModel : WeatherViewModel by viewModels {
        WeatherViewModelFactory(WeatherRepo(RetrofitInstance.weatherService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherAppUsingResponseCallbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cityApiKey = BuildConfig.CITY_API_KEY

        binding.btnSearch.setOnClickListener {
            val cityName = binding.etSearchLocation.text.toString().trim()

            cityViewModel.getCityData(cityApiKey, cityName)
            cityViewModel.cityData.observe(this, Observer {
                if(it.isNotEmpty()){
                    val city = it[0]
                    binding.etLat.text = city.latitude.toString()
                    binding.etLong.text = city.longitude.toString()

                    val weather_ApiKey = BuildConfig.WEATHER_API_KEY
                    binding.btnGetWeatherData.visibility = View.VISIBLE
                    binding.btnGetWeatherData.setOnClickListener {
                        val lat_long = "${city.latitude},${city.longitude}"
                        weatherViewModel.getWeatherData(weather_ApiKey,lat_long,1)

                        weatherViewModel.weatherData.observe(this, Observer { res ->
                            if(res!=null){
                                binding.tvTemp.text = res.toString()
                            }
                        })

                    }
                }
            })
        }
    }
}