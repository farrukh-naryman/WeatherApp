package com.example.weatherapp

import com.example.weatherapp.data.remoe.WeatherResponse

sealed class WeatherUiState {
    object loading: WeatherUiState()

    data class Success (
        val data: WeatherResponse
    ) : WeatherUiState()

    data class Error (
        val message: String
    ) : WeatherUiState()
}