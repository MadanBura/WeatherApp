package com.example.wether_app.noArchitecture

import WeatherService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wether_app.databinding.ActivityWhetherNoArchitectureBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Weather_NoArchitecture : AppCompatActivity() {

    private lateinit var binding: ActivityWhetherNoArchitectureBinding
    private lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhetherNoArchitectureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRetrofit()

        binding.btnGet.setOnClickListener {
            fetchWeatherData()
        }
    }

    private fun initRetrofit() {
        val intercepter = HttpLoggingInterceptor()
        intercepter.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(intercepter).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        weatherService = retrofit.create(WeatherService::class.java)
    }

    private fun fetchWeatherData() {
        if (!::weatherService.isInitialized) {
            Toast.makeText(this, "Weather Service not initialized!", Toast.LENGTH_SHORT).show()
            return
        }

        val key = "55ae7587d2194b30bf364918242111"
        val location = "Kalyan"
        val days = 1
        val aqi = "no"
        val alerts = "no"

        //Asynchronous request call
        val call: Call<WeatherResponse> = weatherService.fetchWeatherData(key, location, days, aqi, alerts)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { weatherResponse ->
                        displayWeatherData(weatherResponse)
                    }
                } else {
                    Toast.makeText(this@Weather_NoArchitecture, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@Weather_NoArchitecture, "Failure: ${t.message}", Toast.LENGTH_SHORT).show()
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
