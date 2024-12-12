package com.example.wether_app.receiver

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wether_app.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class NetworkSampleActivity : AppCompatActivity(),
    ConnectivityReceiver.ConnectivityReceiverListener {

    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_sample)

        // Register the receiver for connectivity changes
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the receiver to avoid memory leaks
        unregisterReceiver(ConnectivityReceiver())
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            // Show "You are offline" Snackbar with indefinite duration
            snackBar = Snackbar.make(
                findViewById(R.id.rootLayout),
                "You are offline",
                Snackbar.LENGTH_INDEFINITE
            ).apply {
                setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, theme))
                show()
            }
        } else {
            // Show "You are online" Snackbar with green background
            snackBar?.dismiss() // Dismiss previous offline Snackbar if any
            snackBar = Snackbar.make(
                findViewById(R.id.rootLayout),
                "Welcome back! You are online now",
                Snackbar.LENGTH_SHORT
            ).apply {
                setBackgroundTint(resources.getColor(android.R.color.holo_green_dark, theme))
                show()
            }
        }
    }

}
