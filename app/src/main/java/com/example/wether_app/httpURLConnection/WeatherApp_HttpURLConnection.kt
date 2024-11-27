package com.example.wether_app.httpURLConnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wether_app.databinding.ActivityWeatherAppHttpUrlconnectionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherApp_HttpURLConnection : AppCompatActivity() {

    private lateinit var binding : ActivityWeatherAppHttpUrlconnectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityWeatherAppHttpUrlconnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Perform network calls using Coroutine (to avoid blocking the main thread)
        GlobalScope.launch(Dispatchers.Main) {
            // GET request example
            val getData = fetchData()
            Log.d("GET Response", getData)

            binding.tvAns.text = getData


//            // POST request example
//            val postDataResponse = postData("Sample data")
//            Log.d("POST Response", postDataResponse)
        }
    }

    private suspend fun fetchData(): String {
        return try {
            withContext(Dispatchers.IO) {
                val url = URL("http://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=Solapur&days=1&aqi=no&alerts=no")
                val urlConnection = url.openConnection() as HttpURLConnection
                try {
                    urlConnection.requestMethod = "GET"
                    urlConnection.connectTimeout = 5000
                    urlConnection.readTimeout = 5000

                    val inputStream = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val response = inputStream.readText()
                    inputStream.close()
                    response
                } finally {
                    urlConnection.disconnect()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error: ${e.message}"
        }
    }
}