package com.dn0ne.weatherapp.data.remote

object HttpRoutes {
    private const val BASE_URL = "https://api.open-meteo.com"
    const val WEATHER = "$BASE_URL/v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl,is_day&wind_speed_unit=ms"
}