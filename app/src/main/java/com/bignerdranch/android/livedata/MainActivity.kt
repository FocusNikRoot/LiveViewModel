package com.bignerdranch.android.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.livedata.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    lateinit var weatherViewModel: WeatherViewModel

    lateinit var bGetResponse : Button
    lateinit var tvTemperature : TextView
    lateinit var tvDescription : TextView
    lateinit var tvCityNameView : TextView
    lateinit var tvCityNameInput : EditText
    lateinit var ivWeatherImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bGetResponse = findViewById(R.id.GetResponse)
        tvTemperature = findViewById(R.id.Temperature)
        tvDescription = findViewById(R.id.Description)
        tvCityNameView = findViewById(R.id.CityNameView)
        tvCityNameInput = findViewById(R.id.CityNameInput)
        ivWeatherImage = findViewById(R.id.WeatherImage)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        bGetResponse.setOnClickListener() {
            if (tvCityNameInput.text.toString() != "") {
                weatherViewModel.retrofitAPI(tvCityNameInput.text.toString())
            }
        }

        weatherViewModel.weatherData.observe(this, Observer { weatherData ->
            tvTemperature.text = "${weatherData.main.temp.toString()} ℃"
            tvCityNameView.text = "В городе ${weatherData.name}"
            tvDescription.text = "Сегодня ${weatherData.weather[0].description}"


            Log.d("ICON", weatherData.weather[0].icon)

            Picasso.get()
                .load("http://openweathermap.org/img/wn/${weatherData.weather[0].icon}@2x.png")
                .into(ivWeatherImage)
        })
    }
}