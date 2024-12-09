package com.example.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.ApiSevice.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.weatherapp.Model.*

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    private val _forecastData = MutableStateFlow<ForecastResponse?>(null)
    private val _alertsData = MutableStateFlow<AlertsResponse?>(null)
    private val _astronomyData = MutableStateFlow<AstronomyResponse?>(null)
    private val _location = MutableStateFlow("")
    val weatherData: StateFlow<WeatherResponse?> = _weatherData
    val forecastData: StateFlow<ForecastResponse?> = _forecastData
    val alertsData: StateFlow<AlertsResponse?> = _alertsData
    val astronomyData: StateFlow<AstronomyResponse?> = _astronomyData
    val location: StateFlow<String> = _location

    fun fetchData(apiKey: String, location: String, days: Int, date: String) {
        viewModelScope.launch {
            try {
                val weatherResponse = RetrofitInstance.api.getCurrentWeather(apiKey, location)
                val forecastResponse: ForecastResponse = RetrofitInstance.api.getForecastWeather(apiKey, location, days)
                val alertsResponse = RetrofitInstance.api.getWeatherAlerts(apiKey, location)
                val astronomyResponse = RetrofitInstance.api.getAstronomyData(apiKey, location, date)
                _weatherData.value = weatherResponse
                _forecastData.value = forecastResponse
                _alertsData.value = alertsResponse
                _astronomyData.value = astronomyResponse
                _location.value = location
                refreshAllData()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun refreshAllData() {
        val apiKey = "8de1fdf928534420a4c220813240112"
        val loc = _location.value

        viewModelScope.launch {
            try {
                _weatherData.value = RetrofitInstance.api.getCurrentWeather(apiKey, loc)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}