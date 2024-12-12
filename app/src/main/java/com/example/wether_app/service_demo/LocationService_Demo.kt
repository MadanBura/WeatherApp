package com.example.wether_app.service_demo

import android.app.Service
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Binder
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.ActivityCompat

class LocationService_Demo : Service() {
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    private val binder = LocationBinder()

    override fun onCreate() {
        super.onCreate()
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val intent = Intent("LocationUpdate")
                intent.putExtra("latitude", location.latitude)
                intent.putExtra("longitude", location.longitude)
                sendBroadcast(intent)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: android.os.Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        // Check if GPS is enabled, else prompt user
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  // Add this flag to fix the issue
            startActivity(intent)
        }

        // Request location updates
        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000L,
                    1f,
                    locationListener
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    inner class LocationBinder : Binder() {
        fun getService(): LocationService_Demo = this@LocationService_Demo
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(locationListener)
    }
}
