package com.example.weatherapp.ApiSevice

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherapp.Model.*

interface WeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") aqi: String = "no",
        @Query("lang") lang: String = "es"

    ): WeatherResponse

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
        @Query("lang") lang: String = "es"
    ): ForecastResponse

    @GET("forecast.json")
    suspend fun getWeatherAlerts(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("alerts") alerts: String = "yes",
        @Query("lang") lang: String = "es"
    ): AlertsResponse

    @GET("astronomy.json")
    suspend fun getAstronomyData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("dt") date: String,
        @Query("lang") lang: String = "es"
    ): AstronomyResponse
}
