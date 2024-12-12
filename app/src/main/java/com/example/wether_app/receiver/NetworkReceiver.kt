package com.example.wether_app.receiver


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.example.wether_app.service_demo.LocationService_Demo

class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if (activeNetwork != null && activeNetwork.isConnected) {
            // Network is connected
            Log.d("NetworkReceiver", "Network is connected. Starting location tracking service.")
            val serviceIntent = Intent(context, LocationService_Demo::class.java)
            context.startService(serviceIntent)
        } else {
            // Network is disconnected
            Log.d("NetworkReceiver", "Network is disconnected. Stopping location tracking service.")
            val serviceIntent = Intent(context, LocationService_Demo::class.java)
            context.stopService(serviceIntent)
        }
    }
}

