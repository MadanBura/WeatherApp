package com.example.wether_app.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wether_app.mvp.model.response.WeatherResponse
import com.example.wether_app.mvp.repository.WeatherRepoMVP
import com.example.wether_app.mvp.utils.RetrofitInstance
import com.example.wether_app.mvp.presenter.WeatherPresenter
import com.example.wether_app.mvp.view.WeatherViewContract
import com.example.wether_app.databinding.ActivityWeatherMvpBinding

class WeatherActivity_MVP : AppCompatActivity(), WeatherViewContract.View {
    private lateinit var binding: ActivityWeatherMvpBinding
    private lateinit var presenter: WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = WeatherPresenter(this, WeatherRepoMVP(RetrofitInstance.weatherService))

        binding.btnGet.setOnClickListener {
            presenter.fetchWeatherData()
        }
    }

    override fun showWeatherData(weatherResponse: WeatherResponse) {
        binding.textView.text = "Location: ${weatherResponse.location.name}\n" +
                "Temperature: ${weatherResponse.current.temp_c}\n" +
                "Condition: ${weatherResponse.current.condition.text}\n"
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}