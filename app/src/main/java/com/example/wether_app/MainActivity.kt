package com.example.wether_app

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wether_app.WebDemo.using_Intent_to_Open_in_Browser.WeatherWebDemo
import com.example.wether_app.WebDemo.using_WebView.WeatherWebViewActivity
import com.example.wether_app.activityResultLauncher.Activity_aa
import com.example.wether_app.asyncTask.Weather_UsingAsync
import com.example.wether_app.databinding.ActivityMainBinding
import com.example.wether_app.httpURLConnection.nestedCall.WeatherNestedActivity
import com.example.wether_app.httpURLConnection.using_MVVM.WeatherActivity_HttpUrlConnection
import com.example.wether_app.mvc.controller.WeatherActivity_MVC
import com.example.wether_app.mvp.WeatherActivity_MVP
import com.example.wether_app.mvvm.WeatherApp_MVVM
import com.example.wether_app.noArchitecture.Weather_NoArchitecture
import com.example.wether_app.mvvm.WeatherActivity_RxJava
import com.example.wether_app.receiver.NetworkReceiver
import com.example.wether_app.receiver.NetworkSampleActivity
import com.example.wether_app.retrofit_withNestedCall.using_LiveData.WeatherNestedUsingLiveData
import com.example.wether_app.retrofit_withNestedCall.using_RxJava.WeatherNestedusingRxJava
import com.example.wether_app.retrofit_with_Response_callback.WeatherApp_using_response_callback
import com.example.wether_app.service_demo.LocationAndBroadCastDemo
import com.example.wether_app.service_demo.LocationService_Demo
import com.example.wether_app.volley.volley_withJson.Weather_WithVolleyJson
import com.example.wether_app.workManagerDemo.UserAddActivity


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

//        binding.btnHttpUrlConnection.setOnClickListener {
//            Intent(this, WeatherApp_HttpURLConnection::class.java).also {
//                startActivity(it)
//            }
//        }

        binding.btnHttpUrlConnection.setOnClickListener {
            Intent(this, WeatherActivity_HttpUrlConnection::class.java).also {
                startActivity(it)
            }
        }

        binding.btnHttpUrlConnectionNestedCall.setOnClickListener {
            Intent(this, WeatherNestedActivity::class.java).also {
                startActivity(it)
            }
        }


        binding.btnNestedCallAsyncTask.setOnClickListener {
            Intent(this, Weather_UsingAsync::class.java).also {
                startActivity(it)
            }
        }


        binding.btnVolleyWithJson.setOnClickListener {
            Intent(this, Weather_WithVolleyJson::class.java).also {
                startActivity(it)
            }
        }

        binding.btnUsingResponseCallback.setOnClickListener {
            Intent(this, WeatherApp_using_response_callback::class.java).also {
                startActivity(it)
            }
        }

        binding.btnRetrofitNestedCallUsingLiveData.setOnClickListener {
            Intent(this, WeatherNestedUsingLiveData::class.java).also {
                startActivity(it)
            }
        }

        binding.btnRetrofitNestedCallUsingRxJava.setOnClickListener {
            Intent(this, WeatherNestedusingRxJava::class.java).also {
                startActivity(it)
            }
        }

        binding.webActivityUsingIntent.setOnClickListener {
            Intent(this, WeatherWebDemo::class.java).also {
                startActivity(it)
            }
        }

        binding.webActivityUsingWebView.setOnClickListener {
            Intent(this, WeatherWebViewActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.resultLauncher.setOnClickListener {
            Intent(this, Activity_aa::class.java).also {
                startActivity(it)
            }
        }

        binding.workManager.setOnClickListener {
            Intent(this, UserAddActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.locationService.setOnClickListener {
           Intent(this, LocationAndBroadCastDemo::class.java).also {
               startActivity(it)
           }
        }

        binding.networkBroadcaster.setOnClickListener {
            Intent(this, NetworkSampleActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}