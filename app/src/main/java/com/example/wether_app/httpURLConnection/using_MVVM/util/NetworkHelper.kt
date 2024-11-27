package com.example.wether_app.httpURLConnection.using_MVVM.util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object NetworkHelper {

    suspend fun fetchData(): String {
        var response = ""

        val thread = Thread {

            try{

                //https://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=19.2502,73.1602&day1
            val url = URL("http://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=Solapur&days=1&aqi=no&alerts=no")
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



//    suspend fun fetchData(): String {
//        return withContext(Dispatchers.IO) {
//            try {
//                val url = URL("http://api.weatherapi.com/v1/forecast.json?key=55ae7587d2194b30bf364918242111&q=Solapur&days=1&aqi=no&alerts=no")
//                val urlConnection = url.openConnection() as HttpURLConnection
//                try {
//                    urlConnection.requestMethod = "GET"
//                    urlConnection.connectTimeout = 5000
//                    urlConnection.readTimeout = 5000
//
//                    val inputStream = BufferedReader(InputStreamReader(urlConnection.inputStream))
//                    val response = inputStream.readText()
//                    inputStream.close()
//                    response
//                } finally {
//                    urlConnection.disconnect()
//                }
//            } catch (e: Exception) {
//                "Error: ${e.message}"
//            }
//        }
//    }


