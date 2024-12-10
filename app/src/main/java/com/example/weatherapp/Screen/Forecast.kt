package com.example.weatherapp.Screen

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import com.example.weatherapp.ViewModel.WeatherViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ForecastScreen(viewModel: WeatherViewModel = viewModel()) {
    val forecastState = viewModel.forecastData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData("8de1fdf928534420a4c220813240112", "Danli", 14, "")
    }
    forecastState.value?.let { forecast ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Pronóstico para ${forecast.location.name}, ${forecast.location.country}",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(forecast.forecast.forecastday) { day ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = "Fecha: ${day.date}")
                        Text(text = "Máx: ${day.day.maxtemp_c}°C")
                        Text(text = "Mín: ${day.day.mintemp_c}°C")
                        Text(text = "Condición: ${day.day.condition.text}")
                    }
                }
            }
        }
    } ?: run {
        CircularProgressIndicator()
    }
}
