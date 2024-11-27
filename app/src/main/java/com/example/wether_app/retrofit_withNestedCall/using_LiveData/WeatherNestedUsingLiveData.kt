package com.example.wether_app.retrofit_withNestedCall.using_LiveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.wether_app.BuildConfig
import com.example.wether_app.databinding.ActivityWeatherNestedUsingLiveDataBinding
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.repository.CItyRepo
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel.CityResponseViewModel
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel.CityResponseViewModelFactory
import android.view.View
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.repository.WeatherResponseRepo
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel.WeatherResponseViewModel
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.viewModel.WeatherResponseViewModelFactory

class WeatherNestedUsingLiveData : AppCompatActivity() {

    private lateinit var binding : ActivityWeatherNestedUsingLiveDataBinding


    private val cityViewModel: CityResponseViewModel by viewModels {
        CityResponseViewModelFactory(cItyRepo = CItyRepo())
    }

    private val weatherViewModel: WeatherResponseViewModel by viewModels {
        WeatherResponseViewModelFactory(weatherRepo = WeatherResponseRepo())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherNestedUsingLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSearch.setOnClickListener {

            val cityName = binding.etSearchLocation.text.toString().trim()
            val city_apiKey = BuildConfig.CITY_API_KEY
            val weather_apiKey = BuildConfig.WEATHER_API_KEY

            cityViewModel.getCityDataFromViewModel(city_apiKey, cityName).observe(this, Observer {

                if(it!=null){

                    val lat = it[0].latitude.toString()
                    val long =  it[0].longitude.toString()
                    binding.etLat.text =lat
                    binding.etLong.text =long


                    binding.btnGetWeatherData.visibility = View.VISIBLE

                    binding.btnGetWeatherData.setOnClickListener {
                        val lat_long = "$lat,$long"
                        weatherViewModel.getWeatherData(weather_apiKey,lat_long).observe(this, Observer {weatherRes ->
                            if(weatherRes!=null){
                                binding.tvTemp.text = weatherRes.toString()
                            }
                        })

                    }



                }

            })


        }


    }
}