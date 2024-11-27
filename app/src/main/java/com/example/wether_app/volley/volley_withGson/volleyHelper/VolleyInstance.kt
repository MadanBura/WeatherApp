package com.example.wether_app.volley.volley_withGson.volleyHelper

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyInstance private constructor(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: VolleyInstance? = null

        fun getInstance(context: Context): VolleyInstance {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleyInstance(context).also {
                    INSTANCE = it
                }
            }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}
