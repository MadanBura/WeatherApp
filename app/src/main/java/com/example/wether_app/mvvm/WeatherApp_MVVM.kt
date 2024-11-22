package com.example.wether_app.mvvm

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wether_app.mvvm.liveData.model.Hour as HourMVVM
import com.example.wether_app.mvvm.liveData.utilsMVVM.HourlyWeatherAdapter
import com.example.wether_app.mvvm.liveData.model.request.WeatherRequest
import com.example.wether_app.mvvm.liveData.model.response.WeatherResponse_MVVM
import com.example.wether_app.mvvm.liveData.repositoryMVVM.WeatherRepo_MVVM
import com.example.wether_app.mvvm.liveData.viewModel.WeatherViewModel
import com.example.wether_app.mvvm.liveData.viewModel.WeatherViewModelFactory
import com.example.wether_app.databinding.ActivityWeatherAppMvvmBinding

class WeatherApp_MVVM : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherAppMvvmBinding
    private lateinit var weatherRequest: WeatherRequest
    private lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter
    private val hourList = mutableListOf<HourMVVM>()

    private val weatherViewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(application, WeatherRepo_MVVM())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherAppMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setUpRecyclerView()

        binding.btnGetWeatherData.setOnClickListener {

            val cityName = binding.CityName.text.toString().trim()

            weatherRequest = WeatherRequest(
                key = "55ae7587d2194b30bf364918242111",
                location = cityName,
                days = 1,
                api = "no",
                alerts = "no"
            )

            fetchWeatherData(weatherRequest)
        }
    }

    private fun setUpRecyclerView() {
        binding.hourlyRecyclerView.layoutManager = LinearLayoutManager(this)
        hourlyWeatherAdapter = HourlyWeatherAdapter(hourList)
        binding.hourlyRecyclerView.adapter = hourlyWeatherAdapter
    }

    private fun fetchWeatherData(weatherRequest: WeatherRequest) {
        binding.progressBar.visibility = View.VISIBLE

        weatherViewModel.getWeatherData(weatherRequest).observe(this, Observer { weatherResponse ->
            binding.progressBar.visibility = View.GONE

            if (weatherResponse != null) {
                setUpUI(weatherResponse)
                Log.e("MVVM", weatherResponse.toString())
            } else {
                binding.currentTemp.text = ""
                binding.currentCondition.text = ""
                binding.dailyTemp.text = ""
                binding.dailyCondition.text = ""
                hourList.clear()
                hourlyWeatherAdapter.notifyDataSetChanged()

                binding.locationName.text = "City not found. Please check the city name."
            }
        })
    }

    private fun setUpUI(weatherResponseMvvm: WeatherResponse_MVVM) {
        binding.locationName.text = "${weatherResponseMvvm.location.name}, ${weatherResponseMvvm.location.region}"
        binding.currentTemp.text = "${weatherResponseMvvm.current.temp_c} °C"
        binding.currentCondition.text = weatherResponseMvvm.current.condition.text
        binding.dailyTemp.text =
            "Max - ${weatherResponseMvvm.forecast.forecastday[0].day.maxtemp_c} °C, " +
                    "Min - ${weatherResponseMvvm.forecast.forecastday[0].day.mintemp_c} °C"
        binding.dailyCondition.text = weatherResponseMvvm.forecast.forecastday[0].day.condition.text

        binding.dailyTemp.visibility = View.VISIBLE
        binding.dailyCondition.visibility = View.VISIBLE

        Log.d("Debug", "Max Temp: ${weatherResponseMvvm.forecast.forecastday[0].day.maxtemp_c}")
        Log.d("Debug", "Min Temp: ${weatherResponseMvvm.forecast.forecastday[0].day.mintemp_c}")
        Log.d("Debug", "Condition: ${weatherResponseMvvm.forecast.forecastday[0].day.condition.text}")


        hourList.clear()
        hourList.addAll(weatherResponseMvvm.forecast.forecastday[0].hour)
        hourlyWeatherAdapter.notifyDataSetChanged()
    }
}
