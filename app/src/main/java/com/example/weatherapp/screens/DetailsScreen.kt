package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.WeatherUiState
import com.example.weatherapp.viewModel.WeatherViewModel

@Composable
fun DetailsScreen(
    cityName: String,
    onBackClick: () -> Unit,
    viewModel: WeatherViewModel
) {

    LaunchedEffect(cityName) {
        viewModel.loadWeather(cityName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        when(val state = viewModel.weatherState) {

            is WeatherUiState.loading -> {

                Text("Loading...")
            }

            is WeatherUiState.Success -> {

                Text(
                    text = "${state.data.main.temp}°C"
                )

                Text(
                    text = "Humidity: ${state.data.main.humidity}%"
                )
            }

            is WeatherUiState.Error -> {

                Text(
                    text = "ERROR: ${state.message}"
                )
            }
        }
    }
}