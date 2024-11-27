package com.example.wether_app.asyncTask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wether_app.databinding.ActivityWeatherUsingAsyncBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.view.View

class Weather_UsingAsync : AppCompatActivity() {

    private lateinit var binding : ActivityWeatherUsingAsyncBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherUsingAsyncBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSearch.setOnClickListener {
            val city = binding.etSearchLocation.text.toString().trim()
            val cityApiUrl = "https://api.api-ninjas.com/v1/city?name=$city"

            FetchCityDataTask().execute(cityApiUrl)
        }

    }

    private inner class FetchCityDataTask : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg p0: String?): String? {
            val apiUrl = p0[0]

            return try {
                val url = URL(apiUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.setRequestProperty(
                    "X-Api-Key",
                    "4aFJnZw4xiuEL8e25Qsrew==hUbqFizYLsLj6bqq"
                )
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000
                connection.readTimeout = 5000

                val inputStream = BufferedReader(InputStreamReader(connection.inputStream))
                val response = inputStream.readText()
                inputStream.close()
                connection.disconnect()
                response
            } catch (e: Exception) {
                Log.e("FetchCityDataTask", e.message ?: "Error fetching city data")
                null
            }
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if (result != null) {
                try {
                    // Parse JSON response
                    val jsonArray = JSONArray(result)
                    if (jsonArray.length() > 0) {
                        val cityData = jsonArray.getJSONObject(0)
                        val latitude = cityData.getDouble("latitude")
                        val longitude = cityData.getDouble("longitude")

                        binding.etLat.text = latitude.toString()
                        binding.etLong.text = longitude.toString()

                        binding.btnGetWeatherData.visibility = View.VISIBLE


                        binding.btnGetWeatherData.setOnClickListener {

                            val weatherApiUrl =
                                "https://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=$latitude,$longitude&days=1"
                            FetchWeatherDataTask().execute(weatherApiUrl)
                        }

                    }
                } catch (e: Exception) {
                    Log.e("JSONParsingError", e.message ?: "Error parsing city data")
                }
            } else {
                Log.e("APIResponse", "No response from city API")
            }
        }

    }



    private inner class FetchWeatherDataTask : AsyncTask<String, Void, String?>() {

        override fun doInBackground(vararg params: String?): String? {
            val apiUrl = params[0]
            return try {
                val url = URL(apiUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000
                connection.readTimeout = 5000

                val inputStream = BufferedReader(InputStreamReader(connection.inputStream))
                val response = inputStream.readText()
                inputStream.close()
                connection.disconnect()
                response
            } catch (e: Exception) {
                Log.e("FetchWeatherDataTask", e.message ?: "Error fetching weather data")
                null
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if (result != null) {
                try {
                    val jsonObject = JSONObject(result)
                    val current = jsonObject.getJSONObject("current")
                    val temperature = current.getDouble("temp_c")

                    binding.tvTemp.text = "$temperature°C"
                } catch (e: Exception) {
                    Log.e("JSONParsingError", e.message ?: "Error parsing weather data")
                }
            } else {
                Log.e("APIResponse", "No response from weather API")
            }
        }
    }
}