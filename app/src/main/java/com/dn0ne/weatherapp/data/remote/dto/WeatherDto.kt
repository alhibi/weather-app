package com.dn0ne.weatherapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherDto(
    @SerialName("hourly")
    val weatherData: WeatherDataDto
)
