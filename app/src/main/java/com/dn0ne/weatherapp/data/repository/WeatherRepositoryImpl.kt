package com.dn0ne.weatherapp.data.repository

import androidx.annotation.Keep
import com.dn0ne.weatherapp.data.mappers.toWeatherInfo
import com.dn0ne.weatherapp.data.remote.WeatherApi
import com.dn0ne.weatherapp.domain.repository.WeatherRepository
import com.dn0ne.weatherapp.domain.util.Resource
import com.dn0ne.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    @Keep
    override suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo> {
        return try {
            println("ERROR RESEARCH: in try block")
            Resource.Success(
                data = api.also {
                    println("ERROR RESEARCH: get to API")
                }.getWeatherData(
                    latitude = latitude,
                    longitude = longitude
                ).also {
                    println("ERROR RESEARCH: after api.getWeatherData()")
                }.toWeatherInfo().also {
                    println("ERROR RESEARCH: in toWeatherInfo().also()")
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Couldn't update the data. Make sure you are connected to the network.")
        }
    }
}