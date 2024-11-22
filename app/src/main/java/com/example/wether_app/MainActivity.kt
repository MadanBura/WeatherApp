package com.example.wether_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wether_app.mvc.controller.WeatherActivity_MVC
import com.example.wether_app.mvp.WeatherActivity_MVP
import com.example.wether_app.mvvm.WeatherApp_MVVM
import com.example.wether_app.NoArchitecture.Weather_NoArchitecture
import com.example.wether_app.databinding.ActivityMainBinding
import com.example.wether_app.mvvm.WeatherActivity_RxJava


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNoArchitecuture.setOnClickListener {
            Intent(this, Weather_NoArchitecture::class.java).also {
                startActivity(it)
            }
        }

        binding.btnMVC.setOnClickListener {
            Intent(this, WeatherActivity_MVC::class.java).also {
                startActivity(it)
            }
        }

        binding.btnMVP.setOnClickListener {
            Intent(this, WeatherActivity_MVP::class.java).also {
                startActivity(it)
            }
        }

        binding.btnMVVMLIVEDATA.setOnClickListener {
            Intent(this, WeatherApp_MVVM::class.java).also {
                startActivity(it)
            }
        }


        binding.btnMVVMRxJava.setOnClickListener {
            Intent(this, WeatherActivity_RxJava::class.java).also {
                startActivity(it)
            }
        }

    }
}