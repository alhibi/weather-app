package com.dn0ne.weatherapp.domain.weather

data class WeatherData(
    val time: kotlinx.datetime.LocalDateTime,
    val isDay: Boolean,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
