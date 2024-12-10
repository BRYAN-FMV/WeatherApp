package com.example.weatherapp.Navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.Screen.AlertsScreen
import com.example.weatherapp.Screen.AstronomyScreen
import com.example.weatherapp.Screen.CurrentWeather
import com.example.weatherapp.Screen.ForecastScreen

@Composable
fun WeatherNavigation(navController: NavHostController){
    val navController = rememberNavController()

    Scaffold (
        topBar = { TopAppBarCustom(navController) },
        modifier = Modifier.fillMaxHeight().fillMaxSize()
    ){ innerPadding ->
        Box (modifier = Modifier.fillMaxHeight().padding(innerPadding)){
            NavHost(navController, startDestination = "current_weather") {
                composable("current_weather") {
                    CurrentWeather(navController)
                }
                composable("forecast_weather") {
                    ForecastScreen()
                }
                composable("weather_alerts") {
                    AlertsScreen()
                }
                composable("astro") {
                    AstronomyScreen()
                }
            }
        }

    }
}