package com.example.weatherapp.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ViewModel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CurrentWeather(navController: NavController, viewModel: WeatherViewModel = viewModel()) {
    val weatherState = viewModel.weatherData.collectAsState()
    val locationState = viewModel.location.collectAsState()
    var city by remember { mutableStateOf(locationState.value) }

    LaunchedEffect(Unit) {
        viewModel.fetchData("8de1fdf928534420a4c220813240112", "Danli",1,"")
    }

    weatherState.value?.let { weather ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                TextField(
                    value = city,
                    onValueChange = { city = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("Buscar ciudad") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { viewModel.fetchData("8de1fdf928534420a4c220813240112", city,1,"") }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
            Card (modifier = Modifier
                .fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Temperatura actual")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${weather.current.temp_c}°C",
                        fontSize = 80.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = weather.current.condition.text, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${weather.location.name}, ${weather.location.country}📍")
                }

            }
            Row {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ){
                    Column(modifier = Modifier
                        .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        AlertsPreview(navController)
                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ){
                    Column(modifier = Modifier
                        .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        AstronomyPreview(navController)
                    }
                }
            }
            ForecastPreview(navController)
        }
    } ?: run {
        CircularProgressIndicator()
    }
}


@Composable
fun ForecastPreview(navController: NavController, viewModel: WeatherViewModel = viewModel()) {
    val forecastState = viewModel.forecastData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData("8de1fdf928534420a4c220813240112", "Danli", 3,"")
    }

    forecastState.value?.let { forecast ->
        Text("Pronóstico",
            fontSize = 20.sp)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { navController.navigate("forecast_weather") }) {
                    Text("Ver pronóstico extendido ->")
                }
            }
        }
    } ?: run {
        CircularProgressIndicator()
    }
}

@Composable
fun AlertsPreview(navController: NavController, viewModel: WeatherViewModel = viewModel()) {
    val alertsState = viewModel.alertsData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData("8de1fdf928534420a4c220813240112", "Danli",1, "")
    }

    alertsState.value?.let { alertsResponse ->
        if (alertsResponse.alerts.alert.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                Column() {
                    Text(text = "⚠️",)
                }
            }
        } else {
            Text(
                text = "Sin alertas.",
                textAlign = TextAlign.Center
            )
        }
        Button(onClick = { navController.navigate("weather_alerts") }) {
            Text("Ver mas")
        }
    }
}

@Composable
fun AstronomyPreview(navController: NavController, viewModel: WeatherViewModel = viewModel()) {
    val astronomyState = viewModel.astronomyData.collectAsState()
    val currentDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(Date())

    LaunchedEffect(Unit) {
        viewModel.fetchData("8de1fdf928534420a4c220813240112", "Danli",1, currentDate)
    }

    astronomyState.value?.let { astronomy ->

        Text(text = "Astronomia")
        Button(onClick = { navController.navigate("astro") }) {
            Text("Ver mas")
        }
    } ?: run {
        CircularProgressIndicator()
    }
}