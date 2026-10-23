package com.example.weatherapp.data.remoe

class WeatherRepository {

    suspend fun getWeather(city: String): WeatherResponse {

        return RetrofitInstance.api.getWeather(
            city = city,
            apiKey = "45462eb599ff8d96efd7490fac6a1b1c"
        )
    }
}