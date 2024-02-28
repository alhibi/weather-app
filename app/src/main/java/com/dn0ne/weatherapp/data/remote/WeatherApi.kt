package com.dn0ne.weatherapp.data.remote

import com.dn0ne.weatherapp.data.remote.dto.WeatherDto

interface WeatherApi {
    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): WeatherDto
}