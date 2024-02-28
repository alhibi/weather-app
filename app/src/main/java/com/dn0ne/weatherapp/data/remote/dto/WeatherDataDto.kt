package com.dn0ne.weatherapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherDataDto(
    val time: List<String>,
    @SerialName("is_day")
    val isDayValues: List<Int>,
    @SerialName("temperature_2m")
    val temperatures: List<Double>,
    @SerialName("weathercode")
    val weatherCodes: List<Int>,
    @SerialName("pressure_msl")
    val pressures: List<Double>,
    @SerialName("windspeed_10m")
    val windSpeeds: List<Double>,
    @SerialName("relativehumidity_2m")
    val humidities: List<Double>
)
