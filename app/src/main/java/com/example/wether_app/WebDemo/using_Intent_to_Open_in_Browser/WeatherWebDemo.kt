package com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.wether_app.BuildConfig
import com.example.wether_app.databinding.ActivityWeatherWebDemoBinding
import android.view.View
import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.repository.CItyWebRepo
import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.viewModels.CityResWebViewModel
import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.viewModels.CityResWebViewModelFactory

class WeatherWebDemo : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherWebDemoBinding


    private val cityWebViewModel : CityResWebViewModel by viewModels {
        CityResWebViewModelFactory(CItyWebRepo())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherWebDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val city_API_KEY = BuildConfig.CITY_API_KEY

        binding.btnCitySerach.setOnClickListener {

            val cityName = binding.etCity.text.toString().trim()

            cityWebViewModel.getCityDataFromViewModel(apiKey = city_API_KEY, cityName).observe(this, Observer {
                if(it!=null){

                    binding.l.visibility = View.VISIBLE
                    val cityName = it[0].name
                    binding.cityName.text = "City Name : $cityName"
                    binding.cityLat.text =  "City lat : ${it[0].latitude.toString() }"
                    binding.cityLong.text =  "City long : ${it[0].longitude.toString() }"
                    binding.cityPoppulation.text = "City Poppulation : ${it[0].population.toString()}"


                   binding.cityName.setOnClickListener {
                       val searchQuery = "https://www.google.com/search?q=${cityName}"
                       val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchQuery))
                       startActivity(intent)
                   }

                }
            })



        }

    }
}