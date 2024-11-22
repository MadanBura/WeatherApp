package com.example.wether_app.mvvm.rxJAVA.utils

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wether_app.mvvm.rxJAVA.model.Hour
import com.example.wether_app.R

class HourlyWeatherAdapter_rx(
    private val hourlyData: List<Hour>
) : RecyclerView.Adapter<HourlyWeatherAdapter_rx.HourlyViewHolder>() {


    class HourlyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val time = itemView.findViewById<TextView>(R.id.tv_time)
        val temp = itemView.findViewById<TextView>(R.id.tv_temperature)
        val windSpeed = itemView.findViewById<TextView>(R.id.tv_wind_speed)
        val humidity = itemView.findViewById<TextView>(R.id.tv_humidity)
        val condition = itemView.findViewById<TextView>(R.id.tv_Condition)
        val icon = itemView.findViewById<ImageView>(R.id.iv_weather_icon)


        fun bind(hour : Hour){
            time.text = hour.time
            temp.text = "${hour.temp_c} °C"
            windSpeed.text = "${hour.wind_kph} kph"
            humidity.text = "${hour.humidity}"
            condition.text = hour.condition.text

            val imageUrl = "https:${hour.condition.icon}"
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(icon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_weather_rx, parent, false)
        return HourlyViewHolder(view)
    }

    override fun getItemCount() = hourlyData.size

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val hour = hourlyData[position]

        holder.bind(hour)
    }


}