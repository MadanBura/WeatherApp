package com.example.wether_app.WebDemo.using_WebView

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.wether_app.BuildConfig
import com.example.wether_app.R
import com.example.wether_app.WebDemo.using_WebView.repositoryWebView.CItyWebViewRepo
import com.example.wether_app.WebDemo.using_WebView.viewModelsWebView.CityResWebviewViewModel
import com.example.wether_app.WebDemo.using_WebView.viewModelsWebView.CityResWebviewViewModelFactory
import com.example.wether_app.databinding.ActivityWeatherWebViewBinding

class WeatherWebViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWeatherWebViewBinding

    private val cityResWebviewViewModel : CityResWebviewViewModel by viewModels {
        CityResWebviewViewModelFactory(CItyWebViewRepo())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val city_API_KEY = BuildConfig.CITY_API_KEY

        binding.btnCitySerach.setOnClickListener {

            val cityName = binding.etCity.text.toString().trim()

            cityResWebviewViewModel.getCityDataFromViewModel(city_API_KEY, cityName).observe(this, Observer {

                if(it!=null){
                    binding.l.visibility = View.VISIBLE
                    val cityName = it[0].name
                    binding.cityName.text = "City Name : $cityName"
                    binding.cityLat.text =  "City lat : ${it[0].latitude.toString() }"
                    binding.cityLong.text =  "City long : ${it[0].longitude.toString() }"
                    binding.cityPoppulation.text = "City Poppulation : ${it[0].population.toString()}"


                    binding.cityName.setOnClickListener {
                        val intent = Intent(this, WebActivity::class.java)
                        intent.putExtra("cityName", cityName)
                        startActivity(intent)

                    }
                }

            })

        }


    }
}