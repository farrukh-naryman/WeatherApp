package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.weatherapp.CityWeather
import com.example.weatherapp.CityWeatherCard
import com.example.weatherapp.ui.theme.BlueButton
import com.example.weatherapp.viewModel.WeatherViewModel

@Composable
fun HomeScreen(
    viewModel: WeatherViewModel,
    onCityClick: (CityWeather) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()
        ) {
            items(viewModel.cities) { city ->
                CityWeatherCard(
                    cityWeather = city,
                    onDeletedClick = {
                        viewModel.deleteCity(city)
                    },
                    onClick = {
                        onCityClick(city)
                    }
                )
            }
        }
        FloatingActionButton(
            onClick = {
                viewModel.openDialog()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
                .size(72.dp),
            containerColor = BlueButton,
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add city",
                tint = Color.White,
            )
        }

        if (viewModel.showDialog) {

            AlertDialog(
                onDismissRequest = {
                    viewModel.closeDialog()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.addCity()
                        }
                    ) {
                        Text(
                            text = "Add"
                        )
                    }

                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            viewModel.closeDialog()
                        }
                    ) {
                        Text(
                            text = "Cancel"
                        )
                    }

                },
                title = {
                    Text(
                        text = "Add City"
                    )
                },
                text = {
                    TextField(
                        value = viewModel.cityName,
                        onValueChange = {
                            viewModel.onCityNameChange(it)
                        }
                    )
                }
            )
        }
    }
}