package com.dn0ne.weatherapp.domain.repository

import com.dn0ne.weatherapp.domain.util.Resource
import com.dn0ne.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo>
}