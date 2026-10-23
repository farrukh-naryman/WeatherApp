package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.screens.DetailsScreen
import com.example.weatherapp.screens.HomeScreen
import com.example.weatherapp.viewModel.WeatherViewModel

@Composable
fun Screen() {

    val navController = rememberNavController()

    val viewModel: WeatherViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onCityClick = { city ->
                    navController.navigate(
                        "details/${city.city}"
                    )
                }
            )
        }
        composable(
            route = "details/{cityName}"
        ) { backStackEntry ->
            val cityName =
                backStackEntry.arguments
                    ?.getString("cityName")
                    ?: ""

            DetailsScreen(
                cityName = cityName,
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }
    }
}

