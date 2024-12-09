package com.example.weatherapp.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ViewModel.WeatherViewModel


@Composable
fun AlertsScreen(viewModel: WeatherViewModel = viewModel()) {
    val alertsState = viewModel.alertsData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData("8de1fdf928534420a4c220813240112", "Danli",1, "")
    }

    alertsState.value?.let { alertsResponse ->
        if (alertsResponse.alerts.alert.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(alertsResponse.alerts.alert) { alert ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "⚠️ ${alert.headline}",)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Tipo: ${alert.msgtype}")
                            Text(text = "Severidad: ${alert.severity}")
                            Text(text = "Áreas afectadas: ${alert.areas}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Descripción: ${alert.description}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Instrucciones: ${alert.instruction}",
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        } else {
            Text(
                text = "No hay alertas meteorológicas en este momento.",
                textAlign = TextAlign.Center
            )
        }
    } ?: run {
        CircularProgressIndicator()
    }
}