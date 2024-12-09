package com.example.weatherapp.Model

data class AstronomyResponse(
    val location: Location,
    val astronomy: Astronomy
)

data class Astronomy(
    val astro: Astro
)

data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    val moon_phase: String,
    val moon_illumination: String
)