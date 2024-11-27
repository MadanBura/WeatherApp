package com.example.wether_app.mvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wether_app.mvc.repository.WeatherRepo
import com.example.wether_app.mvc.utils.RetrofitInstance
import com.example.wether_app.mvc.model.response.WeatherResponse
import com.example.wether_app.databinding.ActivityWeatherMvcBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity_MVC : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherMvcBinding
    private lateinit var weatherRepo: WeatherRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherMvcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherRepo = WeatherRepo(RetrofitInstance.weatherService)

        binding.btnGet.setOnClickListener {
            fetchWeatherData()
        }

    }


    private fun fetchWeatherData() {

        val key = "55ae7587d2194b30bf364918242111"
        val location = "Kalyan"
        val days = 1
        val aqi = "no"
        val alerts = "no"

        //Asynchronous request call
        val call: Call<WeatherResponse> = weatherRepo.fetchWeatherData(key, location, days, aqi, alerts)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { weatherResponse ->
                        displayWeatherData(weatherResponse)
                    }
                } else {
                    Toast.makeText(this@WeatherActivity_MVC, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@WeatherActivity_MVC, "Failure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayWeatherData(weatherResponse: WeatherResponse) {
        binding.textView.text = "Location: ${weatherResponse.location.name}\n" +
                "Temperature: ${weatherResponse.current.temp_c}\n" +
                "Condition: ${weatherResponse.current.condition.text}\n" +
                "$weatherResponse"
    }

}