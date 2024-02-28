package com.dn0ne.weatherapp.data.mappers

import com.dn0ne.weatherapp.data.remote.dto.WeatherDataDto
import com.dn0ne.weatherapp.data.remote.dto.WeatherDto
import com.dn0ne.weatherapp.domain.extensions.now
import com.dn0ne.weatherapp.domain.weather.WeatherData
import com.dn0ne.weatherapp.domain.weather.WeatherInfo
import com.dn0ne.weatherapp.domain.weather.WeatherType
import kotlinx.datetime.LocalDateTime

data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val isDay = isDayValues[index] != 0
        val temperature = temperatures[index]
        val pressure = pressures[index]
        val windSpeed = windSpeeds[index]
        val humidity = humidities[index]
        val weatherType = WeatherType.fromWMO(weatherCodes[index])

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time),
                isDay = isDay,
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = weatherType
            )
        )
    }.groupBy { indexedWeatherData ->
        indexedWeatherData.index / 24
    }.mapValues { entry ->
        entry.value.map { indexedWeatherData -> indexedWeatherData.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = if (now.hour == 23 && now.minute >= 30) {
        weatherDataMap[1]?.get(0)
    } else {
        weatherDataMap[0]?.find {
            val hour = if (now.minute < 30) now.hour else now.hour + 1
            it.time.hour == hour
        }
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}