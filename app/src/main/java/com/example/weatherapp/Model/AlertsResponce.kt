package com.example.weatherapp.Model

data class AlertsResponse(
    val alerts: Alerts
)

data class Alerts(
    val alert: List<Alert>
)

data class Alert(
    val headline: String,
    val msgtype: String,
    val severity: String,
    val areas: String,
    val description: String,
    val instruction: String
)