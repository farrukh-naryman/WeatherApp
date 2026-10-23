package com.example.weatherapp.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.CityWeather
import com.example.weatherapp.WeatherUiState
import com.example.weatherapp.data.remoe.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(

) {
    private val repository = WeatherRepository()

    var weatherState by mutableStateOf<WeatherUiState>(
        WeatherUiState.loading
    )
        private set
    var selectedCity by mutableStateOf<CityWeather?>(null)
        private set

    var showDialog by mutableStateOf(false)
        private set

    var cities by mutableStateOf(listOf<CityWeather>())
        private set

    var cityName by mutableStateOf("")
        private set

    fun openDetails(city: CityWeather) {
        selectedCity = city
    }

    fun closeDetails() {
        selectedCity = null
    }

    fun openDialog() {
        showDialog = true
    }

    fun closeDialog() {
        showDialog = false
    }

    fun onCityNameChange(newValue: String) {
        cityName = newValue
    }

    fun addCity() {
        if (cityName.isNotBlank()) {

            viewModelScope.launch {
                try {
                    val response =
                        repository.getWeather(cityName)

                    cities = cities + CityWeather(
                        city = cityName,
                        temperature =
                           "${response.main.temp.toInt()}°"
                    )
                    cityName = ""

                    showDialog = false
                } catch (e: Exception){
                    Log.e(
                        "Weather",
                        "Error: ${e.message}"
                    )
                }
            }
        }
    }

    fun deleteCity(city: CityWeather) {

        cities = cities - city
    }

    fun loadWeather(city: String){
        viewModelScope.launch {
            weatherState = WeatherUiState.loading

            try {
                val response = repository.getWeather(city)
                weatherState = WeatherUiState.Success(response)
            } catch (e: Exception) {
                Log.e("WEATHER", "Error: ${e.message}")
                weatherState = WeatherUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

