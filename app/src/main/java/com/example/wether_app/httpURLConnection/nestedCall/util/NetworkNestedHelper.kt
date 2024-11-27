package com.example.wether_app.httpURLConnection.nestedCall

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object NetworkNestedHelper {

    suspend fun fetchData(city: String): String {
        return withContext(Dispatchers.IO) {
            var response = ""
            try {
                val url = URL("https://api.api-ninjas.com/v1/city?name=$city")
                val urlConnection = url.openConnection() as HttpURLConnection

                urlConnection.setRequestProperty("X-Api-Key", "4aFJnZw4xiuEL8e25Qsrew==hUbqFizYLsLj6bqq")
                urlConnection.requestMethod = "GET"
                urlConnection.connectTimeout = 5000
                urlConnection.readTimeout = 5000

                val inputStream = BufferedReader(InputStreamReader(urlConnection.inputStream))
                response = inputStream.readText()
                Log.e("Response", "$response")
                inputStream.close()
            } catch (e: Exception) {
                response = "Error: ${e.message}"
            }
            response
        }
    }


    fun fetchWeatherData(lat:Double, long_: Double): String {
        var response = ""

        val thread = Thread {
            try {
                val url = URL("https://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=$lat,$long_&days=1")
                val urlConnection = url.openConnection() as HttpURLConnection
                try {
                    urlConnection.requestMethod = "GET"
                    urlConnection.connectTimeout = 5000
                    urlConnection.readTimeout = 5000

                    val inputStream = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val res = inputStream.readText()
                    inputStream.close()
                    response = res
                } finally {
                    urlConnection.disconnect()
                }
            } catch (e: Exception) {
                "Error: ${e.message}"
            }
        }
        thread.start()
        thread.join()
        return response
    }

}


