package com.example.wether_app.volley.volley_withGson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import android.view.View
import com.example.wether_app.databinding.ActivityWeatherUsingVolleyGsonBinding
import com.example.wether_app.volley.volley_withGson.model.City
import com.example.wether_app.volley.volley_withGson.model.response.WeatherResponse_Volley
import com.example.wether_app.volley.volley_withJson.VolleySingleton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Weather_usingVolleyGson : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherUsingVolleyGsonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherUsingVolleyGsonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSearch.setOnClickListener {
            val cityName = binding.etSearchLocation.text.toString().trim()
            val url = "https://api.api-ninjas.com/v1/city?name=$cityName"
            fetchCityData(url)
        }

    }

    private fun fetchCityData(uri: String) {
        val stringRequest = object : StringRequest(
            Request.Method.GET, uri, Response.Listener { response ->

                val gson = Gson()

                //option 1
//                val cities = gson.fromJson(response, List::class.java)
//
//                val city = cities.get(0)
//                val lat = (city as City_nestedCall).latitude
//                val long = (city as City_nestedCall).latitude


                //option 2
                //typetoken ->
                val type = object : TypeToken<List<City>>() {}.type
                val cities: List<City> = gson.fromJson(response, type)

                if (cities.isNotEmpty()) {
                    val city = cities[0]
                    binding.etLat.text = "${city.latitude}"
                    binding.etLong.text = "${city.longitude}"

                    binding.btnGetWeatherData.visibility = View.VISIBLE

                    binding.btnGetWeatherData.setOnClickListener {
                        val uri =
                            "https://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=${city.latitude},${city.longitude}&days=1"

                        fetchWeatherData(uri)

                    }


                }

            }, Response.ErrorListener { error ->
                Log.e("APIError", "Error: ${error.message}")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Api-Key"] = "4aFJnZw4xiuEL8e25Qsrew==hUbqFizYLsLj6bqq"
                return headers
            }
        }

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)

    }



    private fun fetchWeatherData(uri: String){
        val stringObject = object : StringRequest(
            Request.Method.GET, uri, Response.Listener { response ->

                val gson = Gson()

                //typetoken ->
                val type = object : TypeToken<WeatherResponse_Volley>() {}.type
                val responseObj : WeatherResponse_Volley = gson.fromJson(response, type)

                if(responseObj!=null){
                    binding.tvTemp.text = responseObj.toString()
                }

            }, Response.ErrorListener {error ->
                Log.e("APIError", "Error: ${error.message}")
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["key"] = "55ae7587d2194b30bf364918242111"
                return headers
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(stringObject)

    }

}