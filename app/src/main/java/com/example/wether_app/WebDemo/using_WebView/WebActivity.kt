package com.example.wether_app.WebDemo.using_WebView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.wether_app.R
import com.example.wether_app.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWebView()

        val cityName = intent.getStringExtra("cityName")
        cityName?.let {
            val searchUrl = "https://www.google.com/search?q=$it"
            binding.webView.loadUrl(searchUrl)
        }
    }

    private fun setupWebView() {
        binding.webView.webViewClient = WebViewClient() //for navigation inside browser
        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.getStringExtra("cityName")?.let { cityName ->
            val searchUrl = "https://www.google.com/search?q=$cityName"
            binding.webView.loadUrl(searchUrl)
        }
    }
}