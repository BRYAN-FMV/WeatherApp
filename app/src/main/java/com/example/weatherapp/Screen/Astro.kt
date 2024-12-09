package com.example.weatherapp.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ViewModel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AstronomyScreen(viewModel: WeatherViewModel = viewModel()) {
    val astronomyState = viewModel.astronomyData.collectAsState()
    val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    LaunchedEffect(Unit) {
        viewModel.fetchData("8de1fdf928534420a4c220813240112", "Danli",1, currentDate)
    }

    astronomyState.value?.let { astronomy ->
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Datos Astronómicos para ${astronomy.location.name}, ${astronomy.location.country}",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "☀️ Amanecer: ${astronomy.astronomy.astro.sunrise}")
            Text(text = "☀️ Atardecer: ${astronomy.astronomy.astro.sunset}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "🌙 Salida de la Luna: ${astronomy.astronomy.astro.moonrise}")
            Text(text = "🌙 Puesta de la Luna: ${astronomy.astronomy.astro.moonset}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Fase Lunar: ${astronomy.astronomy.astro.moon_phase}")
            Text(text = "Iluminación de la Luna: ${astronomy.astronomy.astro.moon_illumination}%")
        }
    } ?: run {
        CircularProgressIndicator()
    }
}