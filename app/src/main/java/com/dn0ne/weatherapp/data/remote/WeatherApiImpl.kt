package com.dn0ne.weatherapp.data.remote

import com.dn0ne.weatherapp.data.remote.dto.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class WeatherApiImpl @Inject constructor(
    private val client: HttpClient
): WeatherApi {
    override suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherDto {
        return client.get {
            url(HttpRoutes.WEATHER)
            parameter("latitude", latitude)
            parameter("longitude", longitude)
        }.body<WeatherDto>()
    }
}