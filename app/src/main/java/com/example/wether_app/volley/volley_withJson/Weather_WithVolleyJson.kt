package com.example.wether_app.volley.volley_withJson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.wether_app.databinding.ActivityWeatherWithVolleyJsonBinding
import android.view.View

class Weather_WithVolleyJson : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherWithVolleyJsonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherWithVolleyJsonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val cityName = binding.etSearchLocation.text.toString().trim()
            val url = "https://api.api-ninjas.com/v1/city?name=$cityName"
            fetchCityData(url)
        }

    }

    private fun fetchCityData(url: String) {
        val jsonObjectRequest = object : JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonObject = response.getJSONObject(0)
                    val latitude = jsonObject.getDouble("latitude")
                    val longitude = jsonObject.getDouble("longitude")

                    binding.etLat.text = latitude.toString()
                    binding.etLong.text = longitude.toString()

                    binding.btnGetWeatherData.visibility =View.VISIBLE
                    binding.btnGetWeatherData.setOnClickListener {
                        val uri =
                            "https://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=$latitude,$longitude&days=1"
                        fetchWeatherData(uri)
                    }


                } catch (e: Exception) {
                    Log.e("JSONParsingError", e.message ?: "Error parsing JSON")
                }
            },
            { error ->
                Log.e("VolleyError", error.message ?: "Error fetching data")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Api-Key"] = "4aFJnZw4xiuEL8e25Qsrew==hUbqFizYLsLj6bqq"
                return headers
            }
        }

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    private fun fetchWeatherData(url: String) {
        val jsonObjectRequest = object :JsonObjectRequest(
            Request.Method.GET, url, null,{response ->
                try {

                    val current = response.getJSONObject("current")

                    val currentTemp = current.getDouble("temp_c")

                    binding.tvTemp.text = "Current temp is $currentTemp"

                }catch (e : Exception){
                    Log.e("JSONParsingError", e.message ?: "Error parsing JSON")
                }
            }, { error ->
                Log.e("VolleyError", error.message ?: "Error fetching data")
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["key"] = "55ae7587d2194b30bf364918242111"
                return headers
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


}