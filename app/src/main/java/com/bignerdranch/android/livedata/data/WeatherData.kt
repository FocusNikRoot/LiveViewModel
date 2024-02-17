package com.bignerdranch.android.livedata

import com.bignerdranch.android.livedata.data.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitWeather {
    @GET("weather")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") lang: String
    ): WeatherData

    companion object {
        fun create(): RetrofitWeather {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitWeather::class.java)
        }
    }
}

/*
data class WeatherResponse(
    val list: List<WeatherData>)
*/
data class Weather(
    val description: String,
    val icon: String)

data class WeatherData(
    val weather: List<Weather>,
    val main: Main,
    val name: String)

data class Main(
    val temp: Double,
    val feels_like: Double)
