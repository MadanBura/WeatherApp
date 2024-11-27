package com.example.wether_app.httpURLConnection.nestedCall

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.wether_app.httpURLConnection.nestedCall.viewModel.WeatherNestedViewModel
import com.example.wether_app.databinding.ActivityWeatherNestedBinding

class WeatherNestedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherNestedBinding
    private val weatherViewModel: WeatherNestedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherNestedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {

            val city = binding.etSearchLocation.text.toString().trim()

            if (city.isEmpty()) {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            weatherViewModel.fetchCityData(city)

            weatherViewModel.getResponse.observe(this, Observer { latLongRes ->
                if (latLongRes != null) {
                    binding.etLat.text = latLongRes.lat_.toString()
                    binding.etLong.text = latLongRes.long_.toString()

                    binding.btnGetWeatherData.visibility = View.VISIBLE

                    binding.btnGetWeatherData.setOnClickListener {
                        weatherViewModel.fetchWeatherData(latLongRes.lat_, latLongRes.long_)

                        weatherViewModel.getR.observe(this, Observer { response ->
                            if (response != null) {
                                binding.tvTemp.text = response
                            } else {
                                Log.e("Weather Response", "Weather data is null")
                            }
                        })
                    }

                } else {
                    Toast.makeText(this, "Invalid city or no data found", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}