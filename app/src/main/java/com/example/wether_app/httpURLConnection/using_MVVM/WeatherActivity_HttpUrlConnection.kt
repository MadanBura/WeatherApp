package com.example.wether_app.httpURLConnection.using_MVVM

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.wether_app.httpURLConnection.using_MVVM.Model.Location
import com.example.wether_app.httpURLConnection.using_MVVM.viewModel.WeatherViewModel
import com.example.wether_app.databinding.ActivityWeatherHttpUrlConnectionBinding
import org.json.JSONObject

class WeatherActivity_HttpUrlConnection : AppCompatActivity() {

    private lateinit var binding : ActivityWeatherHttpUrlConnectionBinding
    private val weatherViewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherHttpUrlConnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel.fetchGetData()

        weatherViewModel.getResponse.observe(this, Observer { response ->
            Log.d("GET Response", response)
            if(response!=null){

//                val gson = Gson()
//
//                val jsonObject = gson.fromJson(response, WeatherResponse::class.java)
//                jsonObject.location.name
                val jsonObj = JSONObject(response)
                val location  = jsonObj.getJSONObject("location")
                val country = location.getString("country")
                val lat = location.getDouble("lat")
                val localtime = location.getString("localtime")
                val localtime_epoch = location.getInt("localtime_epoch")
                val lon = location.getDouble("lon")
                val name = location.getString("name")
                val region = location.getString("region")
                val  tz_id = location.getString("tz_id")

                val locationObj = Location(country, lat, localtime, localtime_epoch, lon, name, region, tz_id)

                binding.tv1.text = locationObj.name

            }
        })


    }
}