package com.example.wether_app.mvvm

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wether_app.databinding.ActivityWeatherRxJavaBinding
import com.example.wether_app.mvvm.rxJAVA.model.Hour
import com.example.wether_app.mvvm.rxJAVA.model.request.WeatherRequest
import com.example.wether_app.mvvm.rxJAVA.model.response.WeatherResponse
import com.example.wether_app.mvvm.rxJAVA.repository.WeatherRepo
import com.example.wether_app.mvvm.rxJAVA.utils.HourlyWeatherAdapter_rx
import com.example.wether_app.mvvm.rxJAVA.viewModel.WeatherViewModel
import com.example.wether_app.mvvm.rxJAVA.viewModel.WeatherViewModelFactory

class WeatherActivity_RxJava : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherRxJavaBinding
    private lateinit var weatherRequest: WeatherRequest
    private lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter_rx
    private val hourList = mutableListOf<Hour>()

    private val weatherViewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(application, WeatherRepo())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherRxJavaBinding.inflate(layoutInflater)
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

        observeWeatherData()
    }

    private fun setUpRecyclerView() {
        binding.hourlyRecyclerView.layoutManager = LinearLayoutManager(this)
        hourlyWeatherAdapter = HourlyWeatherAdapter_rx(hourList)
        binding.hourlyRecyclerView.adapter = hourlyWeatherAdapter
    }

    private fun fetchWeatherData(weatherRequest: WeatherRequest) {
        binding.progressBar.visibility = View.VISIBLE

        weatherViewModel.fetchWeatherData(
            "55ae7587d2194b30bf364918242111",
            weatherRequest.location,
            weatherRequest.days,
            weatherRequest.api,
            weatherRequest.alerts
        )
    }

    private fun observeWeatherData() {
        weatherViewModel.weatherData.observe(this) { weatherResponse ->
            binding.progressBar.visibility = View.GONE

            if (weatherResponse != null) {
                setUpUI(weatherResponse)
            } else {
                binding.locationName.text = "Failed to fetch weather data"
            }
        }

        weatherViewModel.error.observe(this) { error ->
            binding.progressBar.visibility = View.GONE
            binding.currentTemp.text = ""
            binding.currentCondition.text = ""
            binding.dailyTemp.text = ""
            binding.dailyCondition.text = ""
            hourList.clear()
            hourlyWeatherAdapter.notifyDataSetChanged()

            binding.locationName.text = "City_nestedCall not found. Please check the city name."
        }

    }

    private fun setUpUI(weatherResponse: WeatherResponse) {
        binding.locationName.text = "${weatherResponse?.location?.name}, ${weatherResponse.location?.region}"
        binding.currentTemp.text = "${weatherResponse.current?.temp_c} °C"
        binding.currentCondition.text = weatherResponse.current?.condition?.text
        binding.dailyTemp.text =
            "Max - ${weatherResponse.forecast!!.forecastday[0].day.maxtemp_c} °C, " +
                    "Min - ${weatherResponse.forecast.forecastday[0].day.mintemp_c} °C"
        binding.dailyCondition.text = weatherResponse.forecast.forecastday[0].day.condition.text

        binding.dailyTemp.visibility = View.VISIBLE
        binding.dailyCondition.visibility = View.VISIBLE

        hourList.clear()
        hourList.addAll(weatherResponse.forecast.forecastday[0].hour)
        hourlyWeatherAdapter.notifyDataSetChanged()
    }
}
