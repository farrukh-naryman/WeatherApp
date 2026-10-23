package com.example.weatherapp.data.remoe

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(

        @Query("q")
        city: String,

        @Query("appId")
        apiKey: String,

        @Query("units")
        units: String = "metric"
    ): WeatherResponse
}