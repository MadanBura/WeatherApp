package com.example.wether_app.volley.volley_withJson

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(context : Context){

    companion object{

        private var INSTANCE : VolleySingleton? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: VolleySingleton(context).also { INSTANCE=it }
            }
    }

    private val requestDeque : RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req : Request<T>){
       requestDeque.add(req)
    }

}